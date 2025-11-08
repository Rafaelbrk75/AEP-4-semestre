<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="mb-8">
        <button
          @click="$emit('back')"
          class="mb-4 flex items-center text-primary-600 dark:text-primary-400 hover:text-primary-700 dark:hover:text-primary-300"
        >
          <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18" />
          </svg>
          Voltar
        </button>
        <h1 class="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-4">
          Cestas Básicas Disponíveis
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300">
          Visualize e solicite cestas básicas disponíveis para resgate
        </p>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="text-center py-20">
        <div class="inline-block animate-spin rounded-full h-16 w-16 border-4 border-yellow-600 border-t-transparent"></div>
        <p class="mt-6 text-gray-600 dark:text-gray-300 text-lg">Carregando cestas disponíveis...</p>
      </div>

      <!-- Baskets Grid -->
      <div v-else-if="availableBaskets.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div 
          v-for="basket in availableBaskets" 
          :key="basket.id" 
          class="card hover:shadow-2xl transition-all duration-300 hover:-translate-y-2"
        >
          <div class="flex items-center mb-4">
            <div class="w-16 h-16 bg-gradient-to-br from-yellow-500 to-yellow-600 rounded-xl flex items-center justify-center mr-4">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
            <div>
              <h3 class="text-xl font-bold text-gray-900 dark:text-white">Cesta Básica</h3>
              <p class="text-sm text-gray-500 dark:text-gray-400">ID: #{{ basket.id }}</p>
            </div>
          </div>

          <div class="mb-4">
            <p class="text-gray-700 dark:text-gray-300 mb-2"><strong>Itens:</strong></p>
            <p class="text-gray-600 dark:text-gray-400 text-sm">{{ basket.items }}</p>
          </div>

          <div v-if="basket.deliveryAddress" class="mb-4 p-3 bg-gray-50 dark:bg-gray-800 rounded-lg">
            <div class="flex items-start">
              <svg class="w-5 h-5 text-yellow-600 mr-2 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
              </svg>
              <div>
                <p class="text-sm font-semibold text-gray-700 dark:text-gray-300">Local de Retirada:</p>
                <p class="text-sm text-gray-600 dark:text-gray-400">{{ basket.deliveryAddress }}</p>
              </div>
            </div>
          </div>

          <div v-if="basket.notes" class="mb-4">
            <p class="text-sm text-gray-600 dark:text-gray-400 italic">{{ basket.notes }}</p>
          </div>

          <button 
            @click="requestBasket(basket)"
            :disabled="requesting"
            class="w-full btn-primary disabled:opacity-50 disabled:cursor-not-allowed"
          >
            Solicitar Cesta
          </button>
        </div>
      </div>

      <!-- Empty State -->
      <div v-else class="text-center py-20">
        <div class="w-24 h-24 bg-gray-200 dark:bg-gray-700 rounded-full flex items-center justify-center mx-auto mb-6">
          <svg class="w-12 h-12 text-gray-400 dark:text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
          </svg>
        </div>
        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-2">Nenhuma cesta disponível no momento</h3>
        <p class="text-gray-600 dark:text-gray-300">Novas cestas básicas serão anunciadas em breve.</p>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'
import { auth } from '../utils/auth'

export default {
  name: 'RedeemBaskets',
  emits: ['back'],
  data() {
    return {
      availableBaskets: [],
      loading: true,
      requesting: false
    }
  },
  mounted() {
    this.loadBaskets()
  },
  methods: {
    async loadBaskets() {
      try {
        this.loading = true
        const response = await api.get('/donations')
        // Filtrar apenas doações disponíveis (status: available ou requested)
        this.availableBaskets = (response.data || []).filter(d => 
          d.status === 'available' || d.status === 'requested'
        )
      } catch (error) {
        console.error('Erro ao carregar cestas:', error)
        this.availableBaskets = []
      } finally {
        this.loading = false
      }
    },
    async requestBasket(basket) {
      if (!confirm('Deseja solicitar esta cesta básica?')) return

      try {
        this.requesting = true
        const user = auth.getUser()
        
        // Atualizar doação com o ID do beneficiário
        const updateData = {
          beneficiaryId: user?.id,
          status: 'pending_approval'
        }
        
        await api.put(`/donations/${basket.id}`, updateData)
        
        alert('Solicitação enviada com sucesso! Aguarde a aprovação.')
        await this.loadBaskets()
      } catch (error) {
        console.error('Erro ao solicitar cesta:', error)
        const errorMsg = error.response?.data?.error || 'Erro ao solicitar cesta. Tente novamente.'
        alert(errorMsg)
      } finally {
        this.requesting = false
      }
    }
  }
}
</script>

