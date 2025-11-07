<template>
  <div class="min-h-screen py-12 bg-gradient-to-br from-gray-50 to-gray-100 dark:from-gray-900 dark:to-gray-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="text-center mb-12">
        <div class="inline-block mb-4">
          <span class="px-4 py-2 bg-primary-100 dark:bg-primary-900/30 text-primary-700 dark:text-primary-300 rounded-full text-sm font-semibold">
            🤝 Organizações Parceiras
          </span>
        </div>
        <h1 class="text-4xl md:text-5xl lg:text-6xl font-extrabold text-gray-900 dark:text-white mb-4">
          Organizações Não Governamentais
        </h1>
        <p class="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto leading-relaxed">
          Conheça as ONGs que oferecem apoio completo e serviços especializados para imigrantes e refugiados
        </p>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="text-center py-20">
        <div class="inline-block animate-spin rounded-full h-16 w-16 border-4 border-primary-600 border-t-transparent"></div>
        <p class="mt-6 text-gray-600 dark:text-gray-300 text-lg">Carregando ONGs...</p>
      </div>

      <!-- ONGs Grid -->
      <div v-else-if="displayedONGs.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
        <div 
          v-for="ong in displayedONGs" 
          :key="ong.id" 
          class="group relative card overflow-hidden hover:shadow-2xl transition-all duration-300 hover:-translate-y-2 border-2 border-transparent hover:border-primary-200"
        >
          <!-- Badge -->
          <div class="absolute top-4 right-4 z-10">
            <span class="px-3 py-1 bg-primary-600 text-white text-xs font-bold rounded-full shadow-lg">
              ONG
            </span>
          </div>

          <!-- Avatar/Icon -->
          <div class="flex items-center mb-6">
            <div class="w-20 h-20 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-2xl flex items-center justify-center shadow-lg group-hover:scale-110 transition-transform duration-300">
              <span class="text-white font-bold text-3xl">{{ ong.name?.charAt(0).toUpperCase() || 'N' }}</span>
            </div>
            <div class="ml-4 flex-1">
              <h3 class="text-2xl font-bold text-gray-900 dark:text-white mb-1">{{ ong.name }}</h3>
              <div class="flex items-center text-sm text-gray-500 dark:text-gray-400">
                <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                {{ ong.location || 'Brasil' }}
              </div>
            </div>
          </div>

          <!-- Description -->
          <p class="text-gray-600 dark:text-gray-300 mb-4 line-clamp-2">{{ ong.description || 'Organização dedicada a ajudar imigrantes e refugiados.' }}</p>

          <!-- Services/Tags -->
          <div class="flex flex-wrap gap-2 mb-4">
            <span 
              v-for="tag in ong.tags" 
              :key="tag"
              class="px-3 py-1 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 text-xs font-semibold rounded-full"
            >
              {{ tag }}
            </span>
          </div>

          <!-- Contact Info -->
          <div class="space-y-2 mb-6 pt-4 border-t border-gray-200 dark:border-gray-700">
            <div class="flex items-center text-gray-600 dark:text-gray-300">
              <svg class="w-5 h-5 mr-3 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
              <span class="text-sm truncate">{{ ong.contact || 'contato@ong.org' }}</span>
            </div>
            <div v-if="ong.phone" class="flex items-center text-gray-600 dark:text-gray-300">
              <svg class="w-5 h-5 mr-3 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
              </svg>
              <span class="text-sm">{{ ong.phone }}</span>
            </div>
          </div>

          <!-- Action Button -->
          <button 
            @click="contactONG(ong)"
            class="w-full px-4 py-3 bg-gradient-to-r from-primary-600 to-primary-700 text-white rounded-xl hover:from-primary-700 hover:to-primary-800 transition-all duration-300 font-bold shadow-lg hover:shadow-xl transform hover:scale-105"
          >
            Entrar em Contato
          </button>
        </div>
      </div>

      <!-- Empty State -->
      <div v-else class="text-center py-20">
        <div class="w-24 h-24 bg-gray-200 dark:bg-gray-700 rounded-full flex items-center justify-center mx-auto mb-6">
          <svg class="w-12 h-12 text-gray-400 dark:text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
          </svg>
        </div>
        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-2">Nenhuma ONG encontrada</h3>
        <p class="text-gray-600 dark:text-gray-300">Comece adicionando uma nova ONG abaixo.</p>
      </div>

      <!-- Add ONG Form -->
      <div class="card max-w-3xl mx-auto shadow-xl">
        <div class="flex items-center mb-6">
          <div class="w-12 h-12 bg-gradient-to-br from-primary-500 to-primary-600 rounded-xl flex items-center justify-center mr-4">
            <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
            </svg>
          </div>
          <h2 class="text-3xl font-extrabold text-gray-900 dark:text-white">Adicionar Nova ONG</h2>
        </div>
        <form @submit.prevent="create" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label for="name" class="block text-sm font-bold text-gray-700 dark:text-gray-300 mb-2">
                Nome da ONG *
              </label>
              <input
                id="name"
                v-model="name"
                type="text"
                placeholder="Digite o nome da ONG"
                required
                class="input-field"
              />
            </div>
            <div>
              <label for="contact" class="block text-sm font-bold text-gray-700 dark:text-gray-300 mb-2">
                Contato (Email) *
              </label>
              <input
                id="contact"
                v-model="contact"
                type="email"
                placeholder="contato@ong.org"
                required
                class="input-field"
              />
            </div>
          </div>
          <div>
            <label for="location" class="block text-sm font-bold text-gray-700 dark:text-gray-300 mb-2">
              Localização
            </label>
            <input
              id="location"
              v-model="location"
              type="text"
              placeholder="Cidade, Estado"
              class="input-field"
            />
          </div>
          <button 
            type="submit" 
            :disabled="creating"
            class="btn-primary w-full disabled:opacity-50 disabled:cursor-not-allowed text-lg py-4"
          >
            <span v-if="creating" class="flex items-center justify-center">
              <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              Adicionando...
            </span>
            <span v-else class="flex items-center justify-center">
              <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
              </svg>
              Adicionar ONG
            </span>
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'

// Dados fictícios
const mockONGs = [
  {
    id: 1,
    name: 'Acolhimento Sem Fronteiras',
    description: 'Organização dedicada a oferecer acolhimento, assistência jurídica e psicológica para refugiados e imigrantes.',
    contact: 'contato@acolhimentosemfronteiras.org',
    phone: '(11) 3456-7890',
    location: 'São Paulo, SP',
    tags: ['Acolhimento', 'Jurídico', 'Psicológico']
  },
  {
    id: 2,
    name: 'Migrações Solidárias',
    description: 'Apoio completo para imigrantes com cursos de português, capacitação profissional e inserção no mercado de trabalho.',
    contact: 'contato@migracoessolidarias.org',
    phone: '(21) 9876-5432',
    location: 'Rio de Janeiro, RJ',
    tags: ['Educação', 'Capacitação', 'Emprego']
  },
  {
    id: 3,
    name: 'Refúgio Humanitário',
    description: 'Assistência humanitária, moradia temporária e suporte para documentação de refugiados.',
    contact: 'contato@refugiohumanitario.org',
    phone: '(85) 3234-5678',
    location: 'Fortaleza, CE',
    tags: ['Moradia', 'Documentação', 'Assistência']
  },
  {
    id: 4,
    name: 'Rede de Apoio ao Imigrante',
    description: 'Rede de voluntários oferecendo apoio social, orientação e conexão com serviços essenciais.',
    contact: 'contato@redeapoioimigrante.org',
    location: 'Belo Horizonte, MG',
    tags: ['Rede', 'Voluntários', 'Orientação']
  },
  {
    id: 5,
    name: 'Casa do Migrante',
    description: 'Acolhimento residencial temporário, refeições e suporte básico para imigrantes em situação de vulnerabilidade.',
    contact: 'contato@casadomigrante.org',
    phone: '(51) 3344-5566',
    location: 'Porto Alegre, RS',
    tags: ['Acolhimento', 'Alimentação', 'Moradia']
  },
  {
    id: 6,
    name: 'Direitos dos Imigrantes',
    description: 'Defesa dos direitos dos imigrantes através de assistência jurídica gratuita e advocacy.',
    contact: 'contato@direitosimigrantes.org',
    location: 'Brasília, DF',
    tags: ['Jurídico', 'Direitos', 'Advocacy']
  }
]

export default {
  name: 'ONGs',
  data() {
    return {
      ongs: [],
      name: '',
      contact: '',
      location: '',
      loading: true,
      creating: false
    }
  },
  computed: {
    displayedONGs() {
      // Combina dados da API com dados fictícios
      return [...this.ongs, ...mockONGs]
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    async load() {
      try {
        this.loading = true
        const res = await api.get('/ongs')
        this.ongs = res.data || []
      } catch (error) {
        console.error('Erro ao carregar ONGs:', error)
        // Se a API falhar, usa apenas dados fictícios
        this.ongs = []
      } finally {
        this.loading = false
      }
    },
    async create() {
      if (!this.name || !this.contact) return
      
      try {
        this.creating = true
        await api.post('/ongs', {
          name: this.name,
          contact: this.contact
        })
        this.name = ''
        this.contact = ''
        this.location = ''
        await this.load()
      } catch (error) {
        console.error('Erro ao criar ONG:', error)
        alert('Erro ao adicionar ONG. Tente novamente.')
      } finally {
        this.creating = false
      }
    },
    contactONG(ong) {
      if (ong.contact) {
        if (ong.contact.includes('@')) {
          window.location.href = `mailto:${ong.contact}`
        } else {
          window.location.href = `tel:${ong.contact}`
        }
      }
    }
  }
}
</script>
