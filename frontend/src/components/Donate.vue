<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="text-center mb-12">
        <h1 class="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-4">
          Faça uma Doação
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
          Sua contribuição ajuda ONGs a continuarem apoiando imigrantes e refugiados
        </p>
      </div>

      <!-- Donation Form -->
      <div class="card mb-8">
        <h2 class="text-2xl font-bold text-gray-900 dark:text-white mb-6">Informações da Doação</h2>
        <form @submit.prevent="handleDonate" class="space-y-6">
          <div>
            <label for="amount" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Valor da Doação (R$)
            </label>
            <div class="relative">
              <span class="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-500 dark:text-gray-400">R$</span>
              <input
                id="amount"
                v-model.number="amount"
                type="number"
                min="10"
                step="10"
                placeholder="0,00"
                required
                class="input-field pl-10"
              />
            </div>
            <div class="flex gap-2 mt-3">
              <button
                type="button"
                @click="amount = 50"
                class="px-4 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 dark:text-gray-300 transition-colors"
              >
                R$ 50
              </button>
              <button
                type="button"
                @click="amount = 100"
                class="px-4 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 dark:text-gray-300 transition-colors"
              >
                R$ 100
              </button>
              <button
                type="button"
                @click="amount = 200"
                class="px-4 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 dark:text-gray-300 transition-colors"
              >
                R$ 200
              </button>
              <button
                type="button"
                @click="amount = 500"
                class="px-4 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-lg hover:bg-gray-50 dark:hover:bg-gray-700 dark:text-gray-300 transition-colors"
              >
                R$ 500
              </button>
            </div>
          </div>

          <div>
            <label for="ong" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Selecionar ONG (Opcional)
            </label>
            <select
              id="ong"
              v-model="selectedONG"
              class="input-field"
            >
              <option value="">Doar para todas as ONGs</option>
              <option v-for="ong in ongs" :key="ong.id" :value="ong.id">
                {{ ong.name }}
              </option>
            </select>
          </div>

          <div>
            <label for="name" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Seu Nome
            </label>
            <input
              id="name"
              v-model="donorName"
              type="text"
              placeholder="Digite seu nome"
              required
              class="input-field"
            />
          </div>

          <div>
            <label for="email" class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">
              Seu Email
            </label>
            <input
              id="email"
              v-model="donorEmail"
              type="email"
              placeholder="seu@email.com"
              required
              class="input-field"
            />
          </div>

          <div class="bg-blue-50 dark:bg-blue-900/30 border border-blue-200 dark:border-blue-800 rounded-lg p-4">
            <div class="flex">
              <svg class="w-5 h-5 text-blue-600 dark:text-blue-400 mr-3 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <div class="text-sm text-blue-800 dark:text-blue-200">
                <p class="font-semibold mb-1">Importante</p>
                <p>Esta é uma demonstração. A funcionalidade de pagamento será integrada em breve.</p>
              </div>
            </div>
          </div>

          <button 
            type="submit" 
            :disabled="donating"
            class="btn-primary w-full disabled:opacity-50 disabled:cursor-not-allowed text-lg py-4"
          >
            <span v-if="donating">Processando...</span>
            <span v-else>Doar R$ {{ amount || 0 }},00</span>
          </button>
        </form>
      </div>

      <!-- Impact Section -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div class="card text-center">
          <div class="text-3xl font-bold text-primary-600 dark:text-primary-400 mb-2">100%</div>
          <div class="text-gray-600 dark:text-gray-300">das doações vão para as ONGs</div>
        </div>
        <div class="card text-center">
          <div class="text-3xl font-bold text-secondary-600 dark:text-secondary-400 mb-2">Transparente</div>
          <div class="text-gray-600 dark:text-gray-300">acompanhe o uso dos recursos</div>
        </div>
        <div class="card text-center">
          <div class="text-3xl font-bold text-yellow-600 dark:text-yellow-400 mb-2">Seguro</div>
          <div class="text-gray-600 dark:text-gray-300">processamento seguro de pagamentos</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'

export default {
  name: 'Donate',
  data() {
    return {
      amount: 100,
      selectedONG: '',
      donorName: '',
      donorEmail: '',
      ongs: [],
      donating: false
    }
  },
  mounted() {
    this.loadONGs()
  },
  methods: {
    async loadONGs() {
      try {
        const res = await api.get('/ongs')
        this.ongs = res.data
      } catch (error) {
        console.error('Erro ao carregar ONGs:', error)
      }
    },
    async handleDonate() {
      if (!this.amount || this.amount < 10) {
        alert('O valor mínimo de doação é R$ 10,00')
        return
      }

      this.donating = true
      try {
        // Criar doação no backend
        const donationData = {
          items: `Doação de R$ ${this.amount},00`,
          beneficiaryId: null, // Será atribuído pela ONG
          donorId: null, // Pode ser anônimo ou buscar do usuário logado
          ongId: this.selectedONG || null,
          status: 'requested',
          deliveryAddress: null
        }
        
        const response = await api.post('/donations', donationData)
        
        alert(`Doação de R$ ${this.amount},00 registrada com sucesso! ID: ${response.data.id}`)
        
        // Reset form
        this.amount = 100
        this.selectedONG = ''
        this.donorName = ''
        this.donorEmail = ''
      } catch (error) {
        console.error('Erro ao processar doação:', error)
        const errorMsg = error.response?.data?.errors || error.response?.data?.error || 'Erro ao processar doação. Tente novamente.'
        alert(typeof errorMsg === 'string' ? errorMsg : JSON.stringify(errorMsg))
      } finally {
        this.donating = false
      }
    }
  }
}
</script>

