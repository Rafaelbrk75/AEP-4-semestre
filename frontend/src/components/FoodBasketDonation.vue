<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="text-center mb-12">
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
          Anunciar Cesta Básica
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
          Anuncie uma doação de cesta básica para que beneficiários possam resgatar
        </p>
      </div>

      <!-- Donation Form -->
      <div class="card">
        <form @submit.prevent="handleDonate" class="space-y-6">
          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Descrição da Cesta Básica *
            </label>
            <textarea
              v-model="form.items"
              rows="4"
              required
              class="input-field"
              placeholder="Descreva os itens da cesta básica (ex: arroz, feijão, macarrão, óleo, açúcar, café, etc.)"
            ></textarea>
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Quantidade de Cestas Disponíveis *
            </label>
            <input
              v-model.number="form.quantity"
              type="number"
              min="1"
              required
              class="input-field"
              placeholder="Ex: 10"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Local de Retirada *
            </label>
            <input
              v-model="form.deliveryAddress"
              type="text"
              required
              class="input-field"
              placeholder="Endereço completo onde as cestas podem ser retiradas"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Data/Horário de Retirada
            </label>
            <input
              v-model="form.pickupDate"
              type="datetime-local"
              class="input-field"
            />
          </div>

          <div>
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Observações Adicionais
            </label>
            <textarea
              v-model="form.notes"
              rows="3"
              class="input-field"
              placeholder="Informações adicionais sobre a doação..."
            ></textarea>
          </div>

          <!-- Mensagens de Erro -->
          <div v-if="errors.length > 0" class="bg-red-50 dark:bg-red-900/30 border border-red-200 dark:border-red-800 rounded-lg p-4">
            <ul class="list-disc list-inside text-sm text-red-800 dark:text-red-200">
              <li v-for="error in errors" :key="error">{{ error }}</li>
            </ul>
          </div>

          <!-- Mensagem de Sucesso -->
          <div v-if="success" class="bg-green-50 dark:bg-green-900/30 border border-green-200 dark:border-green-800 rounded-lg p-4">
            <p class="text-sm text-green-800 dark:text-green-200">
              Cesta básica anunciada com sucesso! Beneficiários poderão visualizar e solicitar.
            </p>
          </div>

          <button 
            type="submit" 
            :disabled="donating"
            class="btn-primary w-full disabled:opacity-50 disabled:cursor-not-allowed text-lg py-4"
          >
            <span v-if="donating">Anunciando...</span>
            <span v-else>Anunciar Cesta Básica</span>
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'
import { auth } from '../utils/auth'

export default {
  name: 'FoodBasketDonation',
  emits: ['back'],
  data() {
    return {
      form: {
        items: '',
        quantity: 1,
        deliveryAddress: '',
        pickupDate: '',
        notes: ''
      },
      errors: [],
      success: false,
      donating: false
    }
  },
  methods: {
    async handleDonate() {
      this.errors = []
      this.success = false
      this.donating = true

      try {
        const user = auth.getUser()
        
        // Criar doação no backend
        const donationData = {
          items: `${this.form.items} (Quantidade: ${this.form.quantity})`,
          beneficiaryId: null, // Será atribuído quando um beneficiário solicitar
          donorId: user?.id || null,
          ongId: user?.role === 'ngo' ? user.id : null,
          status: 'available', // Disponível para resgate
          deliveryAddress: this.form.deliveryAddress,
          notes: this.form.notes,
          pickupDate: this.form.pickupDate
        }
        
        const response = await api.post('/donations', donationData)
        
        this.success = true
        
        // Limpar formulário após 2 segundos
        setTimeout(() => {
          this.form = {
            items: '',
            quantity: 1,
            deliveryAddress: '',
            pickupDate: '',
            notes: ''
          }
          this.success = false
        }, 3000)
      } catch (error) {
        console.error('Erro ao anunciar cesta básica:', error)
        const errorMsg = error.response?.data?.errors || error.response?.data?.error || 'Erro ao anunciar cesta básica. Tente novamente.'
        this.errors = [typeof errorMsg === 'string' ? errorMsg : JSON.stringify(errorMsg)]
      } finally {
        this.donating = false
      }
    }
  }
}
</script>

