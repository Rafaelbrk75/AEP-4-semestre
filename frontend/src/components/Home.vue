<template>
  <div class="min-h-screen">
    <!-- Hero Section -->
    <section class="relative bg-gradient-to-br from-primary-600 via-primary-700 to-secondary-600 dark:from-primary-800 dark:via-primary-900 dark:to-secondary-800 text-white py-24 overflow-hidden">
      <!-- Background Pattern -->
      <div class="absolute inset-0 opacity-10">
        <div class="absolute inset-0" style="background-image: url('data:image/svg+xml,%3Csvg width=&quot;60&quot; height=&quot;60&quot; viewBox=&quot;0 0 60 60&quot; xmlns=&quot;http://www.w3.org/2000/svg&quot;%3E%3Cg fill=&quot;none&quot; fill-rule=&quot;evenodd&quot;%3E%3Cg fill=&quot;%23ffffff&quot; fill-opacity=&quot;1&quot;%3E%3Cpath d=&quot;M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z&quot;/%3E%3C/g%3E%3C/g%3E%3C/svg%3E');"></div>
      </div>
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 relative z-10">
        <div class="text-center">
          <div class="inline-block mb-6">
            <span class="px-4 py-2 bg-white/20 backdrop-blur-sm rounded-full text-sm font-semibold border border-white/30">
              🌍 Conectando Pessoas Globalmente
            </span>
          </div>
          <h1 class="text-5xl md:text-6xl lg:text-7xl font-extrabold mb-6 leading-tight">
            Bem-vindo ao<br />
            <span class="bg-gradient-to-r from-yellow-300 to-yellow-100 bg-clip-text text-transparent">
              SaveToPeople
            </span>
          </h1>
          <p class="text-xl md:text-2xl mb-10 text-primary-100 max-w-3xl mx-auto leading-relaxed">
            Conectando pessoas, transformando vidas. Uma plataforma completa para conectar imigrantes, ONGs e oportunidades de trabalho.
          </p>
          <div class="flex flex-col sm:flex-row gap-4 justify-center">
            <button 
              @click="handleNavigateTo('login')"
              class="group relative px-8 py-4 bg-white text-primary-600 rounded-xl font-bold text-lg hover:bg-gray-50 transition-all duration-300 shadow-xl hover:shadow-2xl hover:scale-105"
            >
              <span class="flex items-center justify-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
                </svg>
                Entrar
              </span>
            </button>
            <button 
              @click="handleNavigateTo('register')"
              class="group px-8 py-4 bg-transparent border-2 border-white text-white rounded-xl font-bold text-lg hover:bg-white hover:text-primary-600 transition-all duration-300 backdrop-blur-sm"
            >
              <span class="flex items-center justify-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z" />
                </svg>
                Cadastrar-se
              </span>
            </button>
          </div>
        </div>
      </div>
      <!-- Wave Divider -->
      <div class="absolute bottom-0 left-0 right-0">
        <svg viewBox="0 0 1440 120" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M0 120L60 105C120 90 240 60 360 45C480 30 600 30 720 37.5C840 45 960 60 1080 67.5C1200 75 1320 75 1380 75L1440 75V120H1380C1320 120 1200 120 1080 120C960 120 840 120 720 120C600 120 480 120 360 120C240 120 120 120 60 120H0Z" fill="white"/>
        </svg>
      </div>
    </section>

    <!-- Stats Section -->
    <section class="py-16 bg-white dark:bg-gray-800 -mt-1">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="grid grid-cols-2 md:grid-cols-4 gap-6">
          <div class="text-center p-6 rounded-2xl bg-gradient-to-br from-primary-50 to-primary-100 dark:from-primary-900/30 dark:to-primary-800/30">
            <div class="text-4xl md:text-5xl font-extrabold text-primary-600 dark:text-primary-400 mb-2">127</div>
            <div class="text-sm font-semibold text-gray-700 dark:text-gray-300">ONGs Cadastradas</div>
          </div>
          <div class="text-center p-6 rounded-2xl bg-gradient-to-br from-secondary-50 to-secondary-100 dark:from-secondary-900/30 dark:to-secondary-800/30">
            <div class="text-4xl md:text-5xl font-extrabold text-secondary-600 dark:text-secondary-400 mb-2">543</div>
            <div class="text-sm font-semibold text-gray-700 dark:text-gray-300">Oportunidades</div>
          </div>
          <div class="text-center p-6 rounded-2xl bg-gradient-to-br from-yellow-50 to-yellow-100 dark:from-yellow-900/30 dark:to-yellow-800/30">
            <div class="text-4xl md:text-5xl font-extrabold text-yellow-600 dark:text-yellow-400 mb-2">2.4K</div>
            <div class="text-sm font-semibold text-gray-700 dark:text-gray-300">Pessoas Ajudadas</div>
          </div>
          <div class="text-center p-6 rounded-2xl bg-gradient-to-br from-purple-50 to-purple-100 dark:from-purple-900/30 dark:to-purple-800/30">
            <div class="text-4xl md:text-5xl font-extrabold text-purple-600 dark:text-purple-400 mb-2">89</div>
            <div class="text-sm font-semibold text-gray-700 dark:text-gray-300">Países Representados</div>
          </div>
        </div>
      </div>
    </section>

    <!-- Features Section -->
    <section class="py-20 bg-gray-50 dark:bg-gray-900">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-16">
          <h2 class="text-4xl md:text-5xl font-extrabold text-gray-900 dark:text-white mb-4">
            Como Funciona
          </h2>
          <p class="text-xl text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
            Nossa plataforma oferece múltiplas formas de conectar pessoas e oportunidades
          </p>
        </div>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <!-- Feature 1 -->
          <div class="group relative card text-center hover:shadow-2xl transition-all duration-300 hover:-translate-y-2">
            <div class="absolute -top-6 left-1/2 transform -translate-x-1/2">
              <div class="w-16 h-16 bg-gradient-to-br from-primary-500 to-primary-600 rounded-2xl flex items-center justify-center shadow-lg group-hover:scale-110 transition-transform duration-300">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
                </svg>
              </div>
            </div>
            <div class="pt-10">
              <h3 class="text-2xl font-bold mb-4 text-gray-900 dark:text-white">ONGs</h3>
              <p class="text-gray-600 dark:text-gray-300 leading-relaxed">
                Conheça organizações não governamentais que oferecem apoio completo e serviços especializados para imigrantes e refugiados.
              </p>
              <ul class="mt-6 text-left space-y-2 text-sm text-gray-600 dark:text-gray-300">
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-primary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Assistência jurídica
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-primary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Suporte psicológico
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-primary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Acolhimento e moradia
                </li>
              </ul>
            </div>
          </div>

          <!-- Feature 2 -->
          <div class="group relative card text-center hover:shadow-2xl transition-all duration-300 hover:-translate-y-2">
            <div class="absolute -top-6 left-1/2 transform -translate-x-1/2">
              <div class="w-16 h-16 bg-gradient-to-br from-secondary-500 to-secondary-600 rounded-2xl flex items-center justify-center shadow-lg group-hover:scale-110 transition-transform duration-300">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                </svg>
              </div>
            </div>
            <div class="pt-10">
              <h3 class="text-2xl font-bold mb-4 text-gray-900 dark:text-white">Oportunidades</h3>
              <p class="text-gray-600 dark:text-gray-300 leading-relaxed">
                Encontre vagas de emprego e oportunidades profissionais adequadas para imigrantes de todas as nacionalidades.
              </p>
              <ul class="mt-6 text-left space-y-2 text-sm text-gray-600 dark:text-gray-300">
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-secondary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Vagas em diversas áreas
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-secondary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Suporte para documentação
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-secondary-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Capacitação profissional
                </li>
              </ul>
            </div>
          </div>

          <!-- Feature 3 -->
          <div class="group relative card text-center hover:shadow-2xl transition-all duration-300 hover:-translate-y-2">
            <div class="absolute -top-6 left-1/2 transform -translate-x-1/2">
              <div class="w-16 h-16 bg-gradient-to-br from-yellow-500 to-yellow-600 rounded-2xl flex items-center justify-center shadow-lg group-hover:scale-110 transition-transform duration-300">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
            </div>
            <div class="pt-10">
              <h3 class="text-2xl font-bold mb-4 text-gray-900 dark:text-white">Doações</h3>
              <p class="text-gray-600 dark:text-gray-300 leading-relaxed">
                Contribua com doações para apoiar ONGs e ajudar pessoas em situação de vulnerabilidade.
              </p>
              <ul class="mt-6 text-left space-y-2 text-sm text-gray-600 dark:text-gray-300">
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-yellow-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Transparência total
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-yellow-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  100% para as ONGs
                </li>
                <li class="flex items-center">
                  <svg class="w-5 h-5 text-yellow-600 mr-2" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd" />
                  </svg>
                  Acompanhamento do impacto
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Testimonials Section -->
    <section class="py-20 bg-white dark:bg-gray-800">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-16">
          <h2 class="text-4xl md:text-5xl font-extrabold text-gray-900 dark:text-white mb-4">
            Histórias de Sucesso
          </h2>
          <p class="text-xl text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
            Veja como estamos transformando vidas todos os dias
          </p>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div class="card">
            <div class="flex items-center mb-4">
              <div class="w-12 h-12 bg-gradient-to-br from-primary-400 to-primary-600 rounded-full flex items-center justify-center text-white font-bold text-xl mr-4">
                M
              </div>
              <div>
                <div class="font-bold text-gray-900 dark:text-white">Maria Silva</div>
                <div class="text-sm text-gray-600 dark:text-gray-400">Venezuela → Brasil</div>
              </div>
            </div>
            <p class="text-gray-700 dark:text-gray-300 italic">"Encontrei apoio jurídico e uma oportunidade de trabalho através desta plataforma. Hoje tenho uma vida estável e posso ajudar minha família."</p>
          </div>
          <div class="card">
            <div class="flex items-center mb-4">
              <div class="w-12 h-12 bg-gradient-to-br from-secondary-400 to-secondary-600 rounded-full flex items-center justify-center text-white font-bold text-xl mr-4">
                A
              </div>
              <div>
                <div class="font-bold text-gray-900 dark:text-white">Ahmed Hassan</div>
                <div class="text-sm text-gray-600 dark:text-gray-400">Síria → Brasil</div>
              </div>
            </div>
            <p class="text-gray-700 dark:text-gray-300 italic">"As ONGs cadastradas me ajudaram com documentação e acolhimento. Estou muito grato por toda a assistência recebida."</p>
          </div>
          <div class="card">
            <div class="flex items-center mb-4">
              <div class="w-12 h-12 bg-gradient-to-br from-yellow-400 to-yellow-600 rounded-full flex items-center justify-center text-white font-bold text-xl mr-4">
                S
              </div>
              <div>
                <div class="font-bold text-gray-900 dark:text-white">Sofia Martinez</div>
                <div class="text-sm text-gray-600 dark:text-gray-400">Colômbia → Brasil</div>
              </div>
            </div>
            <p class="text-gray-700 dark:text-gray-300 italic">"Consegui encontrar trabalho na minha área e hoje estou construindo uma nova vida aqui. Obrigada por facilitar essa conexão!"</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
const props = defineProps({
  onNavigate: {
    type: Function,
    required: true
  }
})

const handleNavigateTo = (view) => {
  console.log('handleNavigateTo called with:', view)
  console.log('onNavigate prop:', props.onNavigate)
  if (props.onNavigate && typeof props.onNavigate === 'function') {
    console.log('Calling onNavigate function')
    props.onNavigate(view)
  } else {
    console.error('onNavigate is not a function:', typeof props.onNavigate)
  }
}

console.log('Home component mounted, onNavigate:', props.onNavigate)
</script>
