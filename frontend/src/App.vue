<template>
  <div class="min-h-screen bg-gray-50 dark:bg-gray-900 transition-colors duration-300">
    <!-- Navigation -->
    <nav class="sticky top-0 z-50 bg-white dark:bg-gray-800 border-b border-gray-200 dark:border-gray-700 shadow-sm">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex justify-between items-center h-16">
          <!-- Logo -->
          <div class="flex items-center space-x-3 cursor-pointer group" @click="currentView = 'home'">
            <div class="w-10 h-10 bg-gradient-to-br from-primary-600 to-secondary-500 rounded-lg flex items-center justify-center transform group-hover:scale-105 transition-transform duration-200">
              <span class="text-white font-bold text-xl">S</span>
            </div>
            <span class="font-bold text-xl text-gray-900 dark:text-white">SaveToPeople</span>
          </div>
          
          <!-- Desktop Navigation -->
          <div class="hidden md:flex items-center space-x-2">
            <button 
              @click="currentView = 'home'" 
              :class="navButtonClass('home')"
              class="px-4 py-2 rounded-lg font-medium transition-all duration-200"
            >
              Início
            </button>
            <button 
              @click="currentView = 'ngos'" 
              :class="navButtonClass('ngos')"
              class="px-4 py-2 rounded-lg font-medium transition-all duration-200"
            >
              ONGs
            </button>
            <button 
              @click="currentView = 'jobs'" 
              :class="navButtonClass('jobs')"
              class="px-4 py-2 rounded-lg font-medium transition-all duration-200"
            >
              Oportunidades
            </button>
            <button 
              @click="currentView = 'donate'" 
              :class="navButtonClass('donate')"
              class="px-4 py-2 rounded-lg font-medium transition-all duration-200"
            >
              Doar
            </button>
            
            <!-- Dark Mode Toggle -->
            <button
              @click="toggleDarkMode"
              class="ml-2 p-2 rounded-lg text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors duration-200"
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

          <!-- Mobile Menu Button -->
          <div class="md:hidden flex items-center space-x-2">
            <button
              @click="toggleDarkMode"
              class="p-2 rounded-lg text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition-colors duration-200"
            >
              <svg v-if="!isDarkMode" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z" />
              </svg>
              <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 3v1m0 16v1m9-9h-1M4 12H3m15.364 6.364l-.707-.707M6.343 6.343l-.707-.707m12.728 0l-.707.707M6.343 17.657l-.707.707M16 12a4 4 0 11-8 0 4 4 0 018 0z" />
              </svg>
            </button>
            <button 
              @click="mobileMenuOpen = !mobileMenuOpen"
              class="w-10 h-10 flex items-center justify-center hover:bg-gray-100 dark:hover:bg-gray-700 rounded-lg transition-colors text-gray-700 dark:text-gray-300"
            >
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path v-if="!mobileMenuOpen" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                <path v-else stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
        </div>
      </div>

      <!-- Mobile Menu -->
      <div v-if="mobileMenuOpen" class="md:hidden border-t border-gray-200 dark:border-gray-700 bg-white dark:bg-gray-800">
        <div class="px-4 py-2 space-y-1">
          <button 
            @click="currentView = 'home'; mobileMenuOpen = false" 
            :class="navButtonClass('home')"
            class="w-full text-left px-4 py-2 rounded-lg font-medium transition-all duration-200"
          >
            Início
          </button>
          <button 
            @click="currentView = 'ngos'; mobileMenuOpen = false" 
            :class="navButtonClass('ngos')"
            class="w-full text-left px-4 py-2 rounded-lg font-medium transition-all duration-200"
          >
            ONGs
          </button>
          <button 
            @click="currentView = 'jobs'; mobileMenuOpen = false" 
            :class="navButtonClass('jobs')"
            class="w-full text-left px-4 py-2 rounded-lg font-medium transition-all duration-200"
          >
            Oportunidades
          </button>
          <button 
            @click="currentView = 'donate'; mobileMenuOpen = false" 
            :class="navButtonClass('donate')"
            class="w-full text-left px-4 py-2 rounded-lg font-medium transition-all duration-200"
          >
            Doar
          </button>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="min-h-[calc(100vh-64px)]">
      <Home v-if="currentView === 'home'" :onNavigate="handleNavigate" />
      <ONGs v-if="currentView === 'ngos'" />
      <Jobs v-if="currentView === 'jobs'" />
      <Donate v-if="currentView === 'donate'" />
    </main>

    <!-- Footer -->
    <footer class="bg-gray-900 dark:bg-gray-950 text-white mt-20">
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
              <li><a href="#" @click.prevent="currentView = 'ngos'" class="hover:text-white transition-colors">ONGs</a></li>
              <li><a href="#" @click.prevent="currentView = 'jobs'" class="hover:text-white transition-colors">Oportunidades</a></li>
              <li><a href="#" @click.prevent="currentView = 'donate'" class="hover:text-white transition-colors">Doar</a></li>
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
import { ref, onBeforeMount, watch } from 'vue'
import Home from './components/Home.vue'
import ONGs from './components/ONGs.vue'
import Jobs from './components/Jobs.vue'
import Donate from './components/Donate.vue'

const currentView = ref('home')
const mobileMenuOpen = ref(false)
const isDarkMode = ref(false)

// Atualizar classe dark no HTML
const updateDarkMode = () => {
  const html = document.documentElement
  if (isDarkMode.value) {
    html.classList.add('dark')
    console.log('Dark mode: ON - classe dark adicionada')
  } else {
    html.classList.remove('dark')
    console.log('Dark mode: OFF - classe dark removida')
  }
}

// Carregar preferência do localStorage ou detectar preferência do sistema
onBeforeMount(() => {
  const saved = localStorage.getItem('darkMode')
  if (saved !== null) {
    isDarkMode.value = saved === 'true'
    console.log('Dark mode carregado do localStorage:', isDarkMode.value)
  } else {
    // Detectar preferência do sistema
    isDarkMode.value = window.matchMedia('(prefers-color-scheme: dark)').matches
    console.log('Dark mode detectado do sistema:', isDarkMode.value)
  }
  updateDarkMode()
})

// Observar mudanças no isDarkMode e atualizar automaticamente
watch(isDarkMode, (newValue) => {
  console.log('isDarkMode mudou para:', newValue)
  updateDarkMode()
}, { immediate: false })

// Toggle dark mode
const toggleDarkMode = () => {
  console.log('toggleDarkMode chamado, valor atual:', isDarkMode.value)
  isDarkMode.value = !isDarkMode.value
  localStorage.setItem('darkMode', isDarkMode.value.toString())
  console.log('Dark mode toggled para:', isDarkMode.value ? 'ON' : 'OFF')
  // Forçar atualização imediata
  updateDarkMode()
}

const navButtonClass = (view) => {
  return currentView.value === view
    ? 'bg-primary-600 text-white shadow-md'
    : 'text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700'
}

const handleNavigate = (view) => {
  console.log('handleNavigate called with:', view)
  currentView.value = view
  console.log('currentView updated to:', currentView.value)
}
</script>
