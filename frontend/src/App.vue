<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 transition-colors duration-300">
    <!-- Navigation (apenas quando autenticado) -->
    <nav v-if="isAuthenticated" class="sticky top-0 z-50 bg-white dark:bg-gray-800 border-b border-gray-200 dark:border-gray-700 shadow-sm">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">
          <!-- Logo -->
          <div class="flex items-center space-x-3 cursor-pointer group" @click="goToDashboard">
            <div class="w-10 h-10 bg-gradient-to-br from-primary-600 to-secondary-500 rounded-lg flex items-center justify-center transform group-hover:scale-105 transition-transform duration-200">
              <span class="text-white font-bold text-xl">S</span>
            </div>
            <span class="font-bold text-xl text-gray-900 dark:text-white">SaveToPeople</span>
          </div>
          
          <!-- User Info & Logout -->
          <div class="flex items-center space-x-4">
            <div class="hidden md:block text-right">
              <p class="text-sm font-semibold text-gray-900 dark:text-white">{{ currentUser?.name }}</p>
              <p class="text-xs text-gray-500 dark:text-gray-400">{{ getRoleLabel(currentUser?.role) }}</p>
            </div>
            <button
              @click="handleLogout"
              class="px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors font-medium"
            >
              Sair
            </button>
            
            <!-- Dark Mode Toggle -->
            <button
              @click="toggleDarkMode"
              class="p-2 rounded-lg text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors duration-200"
              :title="isDarkMode ? 'Modo Claro' : 'Modo Escuro'"
            >
              <svg v-if="!isDarkMode" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z" />
              </svg>
              <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364 6.364l-.707-.707M6.343 6.343l-.707-.707m12.728 0l-.707.707M6.343 17.657l-.707.707M16 12a4 4 0 11-8 0 4 4 0 018 0z" />
              </svg>
            </button>
          </div>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="min-h-[calc(100vh-64px)]">
      <!-- Não autenticado: Mostrar Home, Login ou Register -->
      <template v-if="!isAuthenticated">
        <Home v-if="currentView === 'home'" :onNavigate="handleNavigate" />
        <Login v-if="currentView === 'login'" @loginSuccess="handleLoginSuccess" @showRegister="currentView = 'register'" />
        <Register v-if="currentView === 'register'" @registered="handleRegistered" @cancel="currentView = 'home'" />
      </template>

      <!-- Autenticado: Mostrar Dashboard baseado no perfil -->
      <template v-else>
        <DashboardAdmin v-if="isAdmin" />
        <DashboardDonor v-else-if="isDonorOrNGO" />
        <DashboardBeneficiary v-else-if="isBeneficiary" />
      </template>
    </main>

    <!-- Footer -->
    <footer v-if="!isAuthenticated || currentView === 'home'" class="bg-gray-900 dark:bg-gray-950 text-white mt-20">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div>
            <div class="flex items-center space-x-3 mb-4">
              <div class="w-10 h-10 bg-gradient-to-br from-primary-600 to-secondary-500 rounded-lg flex items-center justify-center">
                <span class="text-white font-bold text-xl">S</span>
              </div>
              <span class="font-bold text-xl">SaveToPeople</span>
            </div>
            <p class="text-gray-400 text-sm">
              Conectando pessoas, transformando vidas. Plataforma para conectar imigrantes, ONGs e oportunidades.
            </p>
          </div>
          <div>
            <h3 class="font-semibold mb-4">Links Rápidos</h3>
            <ul class="space-y-2 text-sm text-gray-400">
              <li><a href="#" @click.prevent="currentView = 'home'" class="hover:text-white transition-colors">Início</a></li>
            </ul>
          </div>
          <div>
            <h3 class="font-semibold mb-4">Contato</h3>
            <p class="text-gray-400 text-sm">
              Entre em contato conosco para mais informações sobre como podemos ajudar.
            </p>
          </div>
        </div>
        <div class="border-t border-gray-800 mt-8 pt-8 text-center text-sm text-gray-400">
          <p>© 2025 SaveToPeople. Todos os direitos reservados.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script setup>
import { ref, onBeforeMount, watch, computed } from 'vue'
import { auth } from './utils/auth'
import Home from './components/Home.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'
import DashboardAdmin from './components/DashboardAdmin.vue'
import DashboardDonor from './components/DashboardDonor.vue'
import DashboardBeneficiary from './components/DashboardBeneficiary.vue'

const currentView = ref('home')
const isDarkMode = ref(false)
const currentUser = ref(auth.getUser())

// Computed properties
const isAuthenticated = computed(() => auth.isAuthenticated())
const isAdmin = computed(() => auth.isAdmin())
const isDonorOrNGO = computed(() => auth.isDonorOrNGO())
const isBeneficiary = computed(() => auth.isBeneficiary())

// Atualizar classe dark no HTML
const updateDarkMode = () => {
  const html = document.documentElement
  if (isDarkMode.value) {
    html.classList.add('dark')
  } else {
    html.classList.remove('dark')
  }
}

// Carregar preferência do localStorage ou detectar preferência do sistema
onBeforeMount(() => {
  const saved = localStorage.getItem('darkMode')
  if (saved !== null) {
    isDarkMode.value = saved === 'true'
  } else {
    isDarkMode.value = window.matchMedia('(prefers-color-scheme: dark)').matches
  }
  updateDarkMode()
  
  // Verificar se usuário está autenticado
  if (auth.isAuthenticated()) {
    currentUser.value = auth.getUser()
  } else {
    currentView.value = 'home'
  }
})

// Observar mudanças no isDarkMode e atualizar automaticamente
watch(isDarkMode, () => {
  updateDarkMode()
}, { immediate: false })

// Toggle dark mode
const toggleDarkMode = () => {
  isDarkMode.value = !isDarkMode.value
  localStorage.setItem('darkMode', isDarkMode.value.toString())
  updateDarkMode()
}

// Handlers
const handleNavigate = (view) => {
  if (!auth.isAuthenticated() && (view === 'login' || view === 'register')) {
    currentView.value = view
  }
}

const handleLoginSuccess = (user) => {
  currentUser.value = user
  currentView.value = 'home'
}

const handleRegistered = (user) => {
  // Após cadastro, fazer login automático
  auth.setUser(user)
  currentUser.value = user
  currentView.value = 'home'
}

const handleLogout = () => {
  auth.logout()
  currentUser.value = null
  currentView.value = 'home'
}

const goToDashboard = () => {
  // Já está no dashboard, não precisa fazer nada
}

const getRoleLabel = (role) => {
  const labels = {
    'beneficiary': 'Beneficiário',
    'donor': 'Doador',
    'ngo': 'ONG',
    'admin': 'Administrador'
  }
  return labels[role] || role
}
</script>
