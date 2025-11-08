<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="mb-8">
        <h1 class="text-4xl md:text-5xl font-bold text-gray-900 dark:text-white mb-2">
          Painel do Beneficiário
        </h1>
        <p class="text-lg text-gray-600 dark:text-gray-300">
          Bem-vindo, {{ user?.name }}! Encontre ajuda e oportunidades.
        </p>
      </div>

      <!-- Cards de Ações -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
        <!-- Resgatar Cestas Básicas -->
        <div 
          @click="currentView = 'redeem-baskets'"
          class="card cursor-pointer hover:shadow-2xl transition-all duration-300 hover:-translate-y-2 border-2 border-transparent hover:border-yellow-200"
        >
          <div class="flex items-center mb-4">
            <div class="w-16 h-16 bg-gradient-to-br from-yellow-500 to-yellow-600 rounded-xl flex items-center justify-center mr-4">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h2l.4 2M7 13h10l4-8H5.4M7 13L5.4 5M7 13l-2.293 2.293c-.63.63-.184 1.707.707 1.707H17m0 0a2 2 0 100 4 2 2 0 000-4zm-8 2a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
            <h3 class="text-2xl font-bold text-gray-900 dark:text-white">Resgatar Cestas</h3>
          </div>
          <p class="text-gray-600 dark:text-gray-300">
            Visualize e solicite cestas básicas disponíveis
          </p>
        </div>

        <!-- Localizar ONGs -->
        <div 
          @click="currentView = 'find-ngos'"
          class="card cursor-pointer hover:shadow-2xl transition-all duration-300 hover:-translate-y-2 border-2 border-transparent hover:border-primary-200"
        >
          <div class="flex items-center mb-4">
            <div class="w-16 h-16 bg-gradient-to-br from-primary-500 to-primary-600 rounded-xl flex items-center justify-center mr-4">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
              </svg>
            </div>
            <h3 class="text-2xl font-bold text-gray-900 dark:text-white">Localizar ONGs</h3>
          </div>
          <p class="text-gray-600 dark:text-gray-300">
            Encontre ONGs e centros de serviços sociais próximos
          </p>
        </div>

        <!-- Candidatar-se a Serviços -->
        <div 
          @click="currentView = 'apply-jobs'"
          class="card cursor-pointer hover:shadow-2xl transition-all duration-300 hover:-translate-y-2 border-2 border-transparent hover:border-secondary-200"
        >
          <div class="flex items-center mb-4">
            <div class="w-16 h-16 bg-gradient-to-br from-secondary-500 to-secondary-600 rounded-xl flex items-center justify-center mr-4">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
            </div>
            <h3 class="text-2xl font-bold text-gray-900 dark:text-white">Candidatar-se</h3>
          </div>
          <p class="text-gray-600 dark:text-gray-300">
            Veja oportunidades de trabalho e serviços disponíveis
          </p>
        </div>
      </div>

      <!-- Conteúdo Dinâmico -->
      <div v-if="currentView === 'redeem-baskets'">
        <RedeemBaskets @back="currentView = null" />
      </div>
      <div v-else-if="currentView === 'find-ngos'">
        <ONGs @back="currentView = null" />
      </div>
      <div v-else-if="currentView === 'apply-jobs'">
        <Jobs @back="currentView = null" />
      </div>
    </div>
  </div>
</template>

<script>
import { auth } from '../utils/auth'
import RedeemBaskets from './RedeemBaskets.vue'
import ONGs from './ONGs.vue'
import Jobs from './Jobs.vue'

export default {
  name: 'DashboardBeneficiary',
  components: {
    RedeemBaskets,
    ONGs,
    Jobs
  },
  data() {
    return {
      user: auth.getUser(),
      currentView: null
    }
  }
}
</script>

