<template>
  <div class="min-h-screen py-12 bg-gradient-to-br from-gray-50 to-gray-100 dark:from-gray-900 dark:to-gray-800">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Header -->
      <div class="text-center mb-12">
        <div class="inline-block mb-4">
          <span class="px-4 py-2 bg-secondary-100 dark:bg-secondary-900/30 text-secondary-700 dark:text-secondary-300 rounded-full text-sm font-semibold">
            💼 Oportunidades de Trabalho
          </span>
        </div>
        <h1 class="text-4xl md:text-5xl lg:text-6xl font-extrabold text-gray-900 dark:text-white mb-4">
          Oportunidades de Trabalho
        </h1>
        <p class="text-xl text-gray-600 dark:text-gray-300 max-w-3xl mx-auto leading-relaxed">
          Encontre vagas de emprego e oportunidades profissionais adequadas para imigrantes de todas as nacionalidades
        </p>
      </div>

      <!-- Loading State -->
      <div v-if="loading" class="text-center py-20">
        <div class="inline-block animate-spin rounded-full h-16 w-16 border-4 border-secondary-600 border-t-transparent"></div>
        <p class="mt-6 text-gray-600 dark:text-gray-300 text-lg">Carregando oportunidades...</p>
      </div>

      <!-- Jobs Grid -->
      <div v-else-if="displayedJobs.length > 0" class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-12">
        <div 
          v-for="job in displayedJobs" 
          :key="job.id" 
          class="group relative card overflow-hidden hover:shadow-2xl transition-all duration-300 hover:-translate-y-2 border-2 border-transparent hover:border-secondary-200"
        >
          <!-- Badge -->
          <div class="absolute top-4 right-4 z-10">
            <span class="px-3 py-1 bg-secondary-600 text-white text-xs font-bold rounded-full shadow-lg">
              {{ job.type || 'Vaga' }}
            </span>
          </div>

          <!-- Icon/Logo -->
          <div class="flex items-start mb-6">
            <div class="w-16 h-16 bg-gradient-to-br from-secondary-500 to-primary-500 rounded-xl flex items-center justify-center shadow-lg group-hover:scale-110 transition-transform duration-300 mr-4">
              <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
            </div>
            <div class="flex-1">
              <h3 class="text-2xl font-bold text-gray-900 dark:text-white mb-2">{{ job.title }}</h3>
              <div class="flex items-center text-gray-600 dark:text-gray-300 mb-2">
                <svg class="w-5 h-5 mr-2 text-secondary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                </svg>
                <span class="text-sm font-medium">{{ job.location || 'Local não especificado' }}</span>
              </div>
              <div v-if="job.company" class="text-sm text-gray-500 dark:text-gray-400">
                {{ job.company }}
              </div>
            </div>
          </div>

          <!-- Description -->
          <p class="text-gray-600 dark:text-gray-300 mb-4 line-clamp-2">{{ job.description || 'Oportunidade de trabalho para imigrantes.' }}</p>

          <!-- Salary/Info -->
          <div v-if="job.salary" class="mb-4 p-3 bg-secondary-50 dark:bg-secondary-900/30 rounded-lg">
            <div class="flex items-center">
              <svg class="w-5 h-5 text-secondary-600 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <span class="font-bold text-secondary-700 dark:text-secondary-300">{{ job.salary }}</span>
            </div>
          </div>

          <!-- Tags/Skills -->
          <div class="flex flex-wrap gap-2 mb-6">
            <span 
              v-for="tag in job.tags" 
              :key="tag"
              class="px-3 py-1 bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300 text-xs font-semibold rounded-full"
            >
              {{ tag }}
            </span>
          </div>

          <!-- Action Button -->
          <button 
            @click="openApplicationModal(job)"
            class="w-full px-4 py-3 bg-gradient-to-r from-secondary-600 to-secondary-700 text-white rounded-xl hover:from-secondary-700 hover:to-secondary-800 transition-all duration-300 font-bold shadow-lg hover:shadow-xl transform hover:scale-105"
          >
            Candidatar-se Agora
          </button>
        </div>
      </div>

      <!-- Empty State -->
      <div v-else class="text-center py-20">
        <div class="w-24 h-24 bg-gray-200 dark:bg-gray-700 rounded-full flex items-center justify-center mx-auto mb-6">
          <svg class="w-12 h-12 text-gray-400 dark:text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
          </svg>
        </div>
        <h3 class="text-xl font-bold text-gray-900 dark:text-white mb-2">Nenhuma oportunidade encontrada</h3>
        <p class="text-gray-600 dark:text-gray-300">Comece adicionando uma nova oportunidade abaixo.</p>
      </div>

      <!-- Add Job Form -->
      <div class="card max-w-3xl mx-auto shadow-xl">
        <div class="flex items-center mb-6">
          <div class="w-12 h-12 bg-gradient-to-br from-secondary-500 to-secondary-600 rounded-xl flex items-center justify-center mr-4">
            <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
            </svg>
          </div>
          <h2 class="text-3xl font-extrabold text-gray-900 dark:text-white">Adicionar Nova Oportunidade</h2>
        </div>
        <form @submit.prevent="create" class="space-y-6">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label for="title" class="block text-sm font-bold text-gray-700 dark:text-gray-300 mb-2">
                Título da Vaga *
              </label>
              <input
                id="title"
                v-model="title"
                type="text"
                placeholder="Ex: Desenvolvedor Web"
                required
                class="input-field"
              />
            </div>
            <div>
              <label for="location" class="block text-sm font-bold text-gray-700 dark:text-gray-300 mb-2">
                Localização *
              </label>
              <input
                id="location"
                v-model="location"
                type="text"
                placeholder="Cidade, Estado"
                required
                class="input-field"
              />
            </div>
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
              Adicionar Oportunidade
            </span>
          </button>
        </form>
      </div>
    </div>

    <!-- Application Modal -->
    <div 
      v-if="showModal" 
      class="fixed inset-0 z-50 overflow-y-auto"
      @click.self="closeModal"
    >
      <!-- Backdrop -->
      <div class="fixed inset-0 bg-black bg-opacity-50 transition-opacity"></div>
      
      <!-- Modal -->
      <div class="flex min-h-full items-center justify-center p-4">
        <div class="relative bg-white dark:bg-gray-800 rounded-2xl shadow-2xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
          <!-- Header -->
          <div class="sticky top-0 bg-gradient-to-r from-secondary-600 to-secondary-700 text-white p-6 rounded-t-2xl">
            <div class="flex items-center justify-between">
              <div>
                <h2 class="text-2xl font-bold mb-1">Candidatura para</h2>
                <p class="text-secondary-100">{{ selectedJob?.title }}</p>
                <p class="text-sm text-secondary-200">{{ selectedJob?.company }} • {{ selectedJob?.location }}</p>
              </div>
              <button 
                @click="closeModal"
                class="w-10 h-10 flex items-center justify-center rounded-full hover:bg-white/20 transition-colors"
              >
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
            </div>
          </div>

          <!-- Form Content -->
          <div class="p-6">
            <!-- Success Message -->
            <div v-if="applicationSubmitted" class="text-center py-12">
              <div class="w-20 h-20 bg-green-100 dark:bg-green-900/30 rounded-full flex items-center justify-center mx-auto mb-6">
                <svg class="w-10 h-10 text-green-600 dark:text-green-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
              </div>
              <h3 class="text-2xl font-bold text-gray-900 dark:text-white mb-2">Candidatura Enviada!</h3>
              <p class="text-gray-600 dark:text-gray-300 mb-6">Sua candidatura foi enviada com sucesso. Entraremos em contato em breve.</p>
              <div class="bg-gray-50 dark:bg-gray-800 rounded-lg p-4 text-left mb-6">
                <p class="text-sm text-gray-600 dark:text-gray-300 mb-2"><strong>Número da candidatura:</strong> #{{ applicationNumber }}</p>
                <p class="text-sm text-gray-600 dark:text-gray-300 mb-2"><strong>Vaga:</strong> {{ selectedJob?.title }}</p>
                <p class="text-sm text-gray-600 dark:text-gray-300"><strong>Data:</strong> {{ submissionDate }}</p>
              </div>
              <button 
                @click="closeModal"
                class="btn-primary"
              >
                Fechar
              </button>
            </div>

            <!-- Application Form -->
            <form v-else @submit.prevent="submitApplication" class="space-y-6">
              <!-- Personal Info Section -->
              <div>
                <h3 class="text-lg font-bold text-gray-900 dark:text-white mb-4 flex items-center">
                  <svg class="w-5 h-5 mr-2 text-secondary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                  </svg>
                  Informações Pessoais
                </h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Nome Completo *</label>
                    <input
                      v-model="applicationForm.fullName"
                      type="text"
                      required
                      class="input-field"
                    />
                  </div>
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Email *</label>
                    <input
                      v-model="applicationForm.email"
                      type="email"
                      required
                      class="input-field"
                    />
                  </div>
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Telefone *</label>
                    <input
                      v-model="applicationForm.phone"
                      type="tel"
                      required
                      class="input-field"
                    />
                  </div>
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">País de Origem</label>
                    <input
                      v-model="applicationForm.country"
                      type="text"
                      class="input-field"
                    />
                  </div>
                </div>
              </div>

              <!-- Professional Info Section -->
              <div>
                <h3 class="text-lg font-bold text-gray-900 dark:text-white mb-4 flex items-center">
                  <svg class="w-5 h-5 mr-2 text-secondary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                  </svg>
                  Informações Profissionais
                </h3>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Experiência (anos)</label>
                    <input
                      v-model="applicationForm.experience"
                      type="text"
                      class="input-field"
                    />
                  </div>
                  <div>
                    <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Nível de Português</label>
                    <select v-model="applicationForm.portugueseLevel" class="input-field">
                      <option>Básico</option>
                      <option>Intermediário</option>
                      <option>Avançado</option>
                      <option>Fluente</option>
                      <option>Nativo</option>
                    </select>
                  </div>
                </div>
                <div class="mt-4">
                  <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Habilidades e Competências</label>
                  <textarea
                    v-model="applicationForm.skills"
                    rows="3"
                    class="input-field"
                    placeholder="Descreva suas principais habilidades e competências..."
                  ></textarea>
                </div>
              </div>

              <!-- Additional Info Section -->
              <div>
                <h3 class="text-lg font-bold text-gray-900 dark:text-white mb-4 flex items-center">
                  <svg class="w-5 h-5 mr-2 text-secondary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                  </svg>
                  Informações Adicionais
                </h3>
                <div>
                  <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Por que você se interessa por esta vaga?</label>
                  <textarea
                    v-model="applicationForm.motivation"
                    rows="4"
                    class="input-field"
                    placeholder="Conte-nos um pouco sobre você e por que você seria um bom candidato para esta posição..."
                  ></textarea>
                </div>
                <div class="mt-4">
                  <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">Status da Documentação</label>
                  <select v-model="applicationForm.documentationStatus" class="input-field">
                    <option>Documentação completa</option>
                    <option>Documentação em processo</option>
                    <option>Documentação pendente</option>
                  </select>
                </div>
              </div>

              <!-- Submit Button -->
              <div class="flex gap-4 pt-4">
                <button 
                  type="button"
                  @click="closeModal"
                  class="flex-1 px-6 py-3 bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200 rounded-xl hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors font-semibold"
                >
                  Cancelar
                </button>
                <button 
                  type="submit"
                  :disabled="submitting"
                  class="flex-1 btn-primary disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <span v-if="submitting" class="flex items-center justify-center">
                    <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                      <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                      <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                    </svg>
                    Enviando...
                  </span>
                  <span v-else class="flex items-center justify-center">
                    <svg class="w-5 h-5 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                    </svg>
                    Enviar Candidatura
                  </span>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'

// Dados fictícios
const mockJobs = [
  {
    id: 1,
    title: 'Desenvolvedor Full Stack',
    company: 'Tech Solutions Brasil',
    location: 'São Paulo, SP',
    type: 'CLT',
    salary: 'R$ 5.000 - R$ 8.000',
    description: 'Oportunidade para desenvolvedor full stack com experiência em React, Node.js e banco de dados. Aceitamos imigrantes com documentação em processo.',
    tags: ['Tecnologia', 'Remoto', 'CLT']
  },
  {
    id: 2,
    title: 'Auxiliar de Cozinha',
    company: 'Restaurante Sabores do Mundo',
    location: 'Rio de Janeiro, RJ',
    type: 'CLT',
    salary: 'R$ 1.500 - R$ 2.200',
    description: 'Vaga para auxiliar de cozinha. Não é necessário experiência prévia. Oferecemos treinamento e suporte para documentação.',
    tags: ['Gastronomia', 'Treinamento', 'CLT']
  },
  {
    id: 3,
    title: 'Atendente de Vendas',
    company: 'Comércio Popular',
    location: 'Belo Horizonte, MG',
    type: 'CLT',
    salary: 'R$ 1.200 - R$ 1.800',
    description: 'Procuramos pessoa comunicativa para atendimento ao cliente. Aceitamos iniciantes e oferecemos capacitação.',
    tags: ['Vendas', 'Atendimento', 'CLT']
  },
  {
    id: 4,
    title: 'Professor de Idiomas',
    company: 'Escola de Idiomas Global',
    location: 'Curitiba, PR',
    type: 'PJ',
    salary: 'R$ 3.000 - R$ 5.000',
    description: 'Oportunidade para professores nativos ou com fluência em inglês, espanhol ou francês. Horários flexíveis.',
    tags: ['Educação', 'Idiomas', 'PJ']
  },
  {
    id: 5,
    title: 'Assistente Administrativo',
    company: 'Empresa Multinacional',
    location: 'Porto Alegre, RS',
    type: 'CLT',
    salary: 'R$ 2.000 - R$ 3.500',
    description: 'Vaga para assistente administrativo com conhecimento básico de informática. Ambiente inclusivo e diversificado.',
    tags: ['Administrativo', 'Escritório', 'CLT']
  },
  {
    id: 6,
    title: 'Motorista de Aplicativo',
    company: 'Freelance',
    location: 'Brasília, DF',
    type: 'PJ',
    salary: 'R$ 2.500 - R$ 4.000',
    description: 'Trabalhe como motorista de aplicativo. Flexibilidade de horários e aceitação para imigrantes com CNH válida.',
    tags: ['Transporte', 'Flexível', 'PJ']
  },
  {
    id: 7,
    title: 'Costureira(o)',
    company: 'Ateliê de Moda',
    location: 'Fortaleza, CE',
    type: 'CLT',
    salary: 'R$ 1.800 - R$ 2.500',
    description: 'Procuramos costureira(o) com experiência em confecção. Ambiente acolhedor e respeito à diversidade.',
    tags: ['Moda', 'Costura', 'CLT']
  },
  {
    id: 8,
    title: 'Tradutor/Intérprete',
    company: 'Agência de Tradução',
    location: 'São Paulo, SP',
    type: 'PJ',
    salary: 'R$ 4.000 - R$ 7.000',
    description: 'Oportunidade para tradutores e intérpretes em diversas línguas. Trabalho remoto disponível.',
    tags: ['Tradução', 'Remoto', 'PJ']
  },
  {
    id: 9,
    title: 'Operador de Produção',
    company: 'Indústria Alimentícia',
    location: 'Campinas, SP',
    type: 'CLT',
    salary: 'R$ 1.600 - R$ 2.300',
    description: 'Vaga em indústria alimentícia. Treinamento fornecido. Aceita imigrantes em processo de documentação.',
    tags: ['Indústria', 'Produção', 'CLT']
  }
]

// Dados fictícios para o formulário
const mockCandidateData = {
  fullName: 'Maria Silva Santos',
  email: 'maria.silva@email.com',
  phone: '(11) 98765-4321',
  country: 'Venezuela',
  experience: '3 anos',
  portugueseLevel: 'Intermediário',
  skills: 'Experiência em desenvolvimento web, conhecimento em React, JavaScript, Node.js, banco de dados SQL e NoSQL. Trabalho em equipe e comunicação.',
  motivation: 'Sou uma desenvolvedora com experiência em projetos web e estou buscando uma oportunidade para crescer profissionalmente no Brasil. Tenho grande interesse em trabalhar com tecnologias modernas e contribuir para o sucesso da equipe.',
  documentationStatus: 'Documentação em processo'
}

export default {
  name: 'Jobs',
  data() {
    return {
      jobs: [],
      title: '',
      location: '',
      loading: true,
      creating: false,
      showModal: false,
      selectedJob: null,
      applicationSubmitted: false,
      submitting: false,
      applicationNumber: null,
      submissionDate: null,
      applicationForm: {
        fullName: mockCandidateData.fullName,
        email: mockCandidateData.email,
        phone: mockCandidateData.phone,
        country: mockCandidateData.country,
        experience: mockCandidateData.experience,
        portugueseLevel: mockCandidateData.portugueseLevel,
        skills: mockCandidateData.skills,
        motivation: mockCandidateData.motivation,
        documentationStatus: mockCandidateData.documentationStatus
      }
    }
  },
  computed: {
    displayedJobs() {
      return [...this.jobs, ...mockJobs]
    }
  },
  mounted() {
    this.load()
  },
  methods: {
    async load() {
      try {
        this.loading = true
        const res = await api.get('/jobs')
        this.jobs = res.data || []
      } catch (error) {
        console.error('Erro ao carregar oportunidades:', error)
        this.jobs = []
      } finally {
        this.loading = false
      }
    },
    async create() {
      if (!this.title || !this.location) return
      
      try {
        this.creating = true
        await api.post('/jobs', {
          title: this.title,
          location: this.location
        })
        this.title = ''
        this.location = ''
        await this.load()
      } catch (error) {
        console.error('Erro ao criar oportunidade:', error)
        alert('Erro ao adicionar oportunidade. Tente novamente.')
      } finally {
        this.creating = false
      }
    },
    openApplicationModal(job) {
      this.selectedJob = job
      this.showModal = true
      this.applicationSubmitted = false
      // Reset form to mock data
      this.applicationForm = {
        fullName: mockCandidateData.fullName,
        email: mockCandidateData.email,
        phone: mockCandidateData.phone,
        country: mockCandidateData.country,
        experience: mockCandidateData.experience,
        portugueseLevel: mockCandidateData.portugueseLevel,
        skills: mockCandidateData.skills,
        motivation: mockCandidateData.motivation,
        documentationStatus: mockCandidateData.documentationStatus
      }
    },
    closeModal() {
      this.showModal = false
      this.selectedJob = null
      this.applicationSubmitted = false
      setTimeout(() => {
        this.applicationNumber = null
        this.submissionDate = null
      }, 300)
    },
    async submitApplication() {
      try {
        this.submitting = true
        
        // Preparar dados da candidatura
        const applicationData = {
          jobId: this.selectedJob.id,
          userId: 1, // TODO: Pegar do usuário logado quando autenticação estiver implementada
          name: this.applicationForm.fullName,
          email: this.applicationForm.email,
          phone: this.applicationForm.phone,
          countryOfOrigin: this.applicationForm.country,
          experience: this.applicationForm.experience,
          portugueseLevel: this.applicationForm.portugueseLevel,
          skills: this.applicationForm.skills,
          motivation: this.applicationForm.motivation,
          hasDocumentation: this.applicationForm.documentationStatus === 'Documentação completa',
          status: 'pending'
        }
        
        // Enviar para o backend
        const response = await api.post('/applications', applicationData)
        
        this.applicationNumber = response.data.applicationNumber
        this.submissionDate = new Date(response.data.createdAt).toLocaleDateString('pt-BR', {
          day: '2-digit',
          month: '2-digit',
          year: 'numeric',
          hour: '2-digit',
          minute: '2-digit'
        })
        
        this.applicationSubmitted = true
      } catch (error) {
        console.error('Erro ao enviar candidatura:', error)
        const errorMsg = error.response?.data?.errors || error.response?.data?.error || 'Erro ao enviar candidatura. Tente novamente.'
        alert(typeof errorMsg === 'string' ? errorMsg : JSON.stringify(errorMsg))
      } finally {
        this.submitting = false
      }
    }
  }
}
</script>
