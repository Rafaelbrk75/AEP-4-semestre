<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900">
    <div class="max-w-2xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="card">
        <div class="text-center mb-8">
          <h1 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-2">
            Criar Conta
          </h1>
          <p class="text-gray-600 dark:text-gray-300">
            Cadastre-se para acessar todas as funcionalidades da plataforma
          </p>
        </div>

        <form @submit.prevent="handleRegister" class="space-y-6">
          <!-- Tipo de Usuário -->
          <div>
            <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
              Tipo de Conta *
            </label>
            <select
              v-model="form.role"
              required
              class="input-field"
            >
              <option value="">Selecione...</option>
              <option value="beneficiary">Beneficiário (Imigrante/Refugiado)</option>
              <option value="donor">Doador</option>
              <option value="ngo">ONG</option>
              <option value="admin">Administrador</option>
            </select>
          </div>

          <!-- Informações Básicas -->
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                Nome Completo *
              </label>
              <input
                v-model="form.name"
                type="text"
                required
                class="input-field"
                placeholder="Seu nome completo"
              />
            </div>
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                Email *
              </label>
              <input
                v-model="form.email"
                type="email"
                required
                class="input-field"
                placeholder="seu@email.com"
              />
            </div>
          </div>

          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                Senha *
              </label>
              <input
                v-model="form.password"
                type="password"
                required
                minlength="6"
                class="input-field"
                placeholder="Mínimo 6 caracteres"
              />
            </div>
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                Telefone
              </label>
              <input
                v-model="form.phone"
                type="tel"
                class="input-field"
                placeholder="(11) 98765-4321"
              />
            </div>
          </div>

          <div>
            <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
              Endereço
            </label>
            <input
              v-model="form.address"
              type="text"
              class="input-field"
              placeholder="Rua, número, bairro, cidade"
            />
          </div>

          <!-- Campos específicos para beneficiários -->
          <div v-if="form.role === 'beneficiary'" class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                País de Origem
              </label>
              <input
                v-model="form.countryOfOrigin"
                type="text"
                class="input-field"
                placeholder="Ex: Venezuela, Haiti, etc."
              />
            </div>
            <div>
              <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
                Número de Documento
              </label>
              <input
                v-model="form.documentNumber"
                type="text"
                class="input-field"
                placeholder="CPF, RNE, etc."
              />
            </div>
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
              Conta criada com sucesso! Você pode fazer login agora.
            </p>
          </div>

          <!-- Botões -->
          <div class="flex gap-4 pt-4">
            <button
              type="button"
              @click="$emit('cancel')"
              class="flex-1 px-6 py-3 bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200 rounded-xl hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors font-semibold"
            >
              Cancelar
            </button>
            <button
              type="submit"
              :disabled="registering"
              class="flex-1 btn-primary disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <span v-if="registering" class="flex items-center justify-center">
                <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                  <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                  <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                </svg>
                Criando conta...
              </span>
              <span v-else>Criar Conta</span>
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'

export default {
  name: 'Register',
  emits: ['cancel', 'registered'],
  data() {
    return {
      form: {
        name: '',
        email: '',
        password: '',
        phone: '',
        address: '',
        role: '',
        countryOfOrigin: '',
        documentNumber: ''
      },
      errors: [],
      success: false,
      registering: false
    }
  },
  methods: {
    async handleRegister() {
      this.errors = []
      this.success = false
      this.registering = true

      try {
        const response = await api.post('/auth/register', this.form)
        
        this.success = true
        // Emitir evento com o usuário registrado
        this.$emit('registered', response.data)
        
        // Limpar formulário após 2 segundos
        setTimeout(() => {
          this.$emit('cancel')
        }, 2000)
      } catch (error) {
        console.error('Erro ao criar conta:', error)
        
        if (error.response?.data?.errors) {
          // Erros de validação
          const validationErrors = error.response.data.errors
          this.errors = Object.values(validationErrors).flat()
        } else if (error.response?.data?.error) {
          this.errors = [error.response.data.error]
        } else {
          this.errors = ['Erro ao criar conta. Tente novamente.']
        }
      } finally {
        this.registering = false
      }
    }
  }
}
</script>

