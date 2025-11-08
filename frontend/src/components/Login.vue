<template>
  <div class="min-h-screen py-12 bg-gray-50 dark:bg-gray-900 flex items-center justify-center">
    <div class="max-w-md w-full mx-auto px-4 sm:px-6 lg:px-8">
      <div class="card">
        <div class="text-center mb-8">
          <div class="w-16 h-16 bg-gradient-to-br from-primary-600 to-secondary-500 rounded-2xl flex items-center justify-center mx-auto mb-4">
            <span class="text-white font-bold text-2xl">S</span>
          </div>
          <h1 class="text-3xl md:text-4xl font-bold text-gray-900 dark:text-white mb-2">
            Entrar
          </h1>
          <p class="text-gray-600 dark:text-gray-300">
            Acesse sua conta para continuar
          </p>
        </div>

        <form @submit.prevent="handleLogin" class="space-y-6">
          <div>
            <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
              Email *
            </label>
            <input
              v-model="form.email"
              type="email"
              required
              autocomplete="email"
              class="input-field"
              placeholder="seu@email.com"
            />
          </div>

          <div>
            <label class="block text-sm font-semibold text-gray-700 dark:text-gray-300 mb-2">
              Senha *
            </label>
            <input
              v-model="form.password"
              type="password"
              required
              autocomplete="current-password"
              class="input-field"
              placeholder="Digite sua senha"
            />
          </div>

          <!-- Mensagens de Erro -->
          <div v-if="errors.length > 0" class="bg-red-50 dark:bg-red-900/30 border border-red-200 dark:border-red-800 rounded-lg p-4">
            <ul class="list-disc list-inside text-sm text-red-800 dark:text-red-200">
              <li v-for="error in errors" :key="error">{{ error }}</li>
            </ul>
          </div>

          <!-- Botões -->
          <div class="flex flex-col gap-4 pt-4">
            <button
              type="submit"
              :disabled="loggingIn"
              class="btn-primary disabled:opacity-50 disabled:cursor-not-allowed"
            >
              <span v-if="loggingIn" class="flex items-center justify-center">
                <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                  <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                  <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                </svg>
                Entrando...
              </span>
              <span v-else>Entrar</span>
            </button>
            <button
              type="button"
              @click="$emit('showRegister')"
              class="px-6 py-3 bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200 rounded-xl hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors font-semibold"
            >
              Não tem conta? Cadastre-se
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../api/axios'
import { auth } from '../utils/auth'

export default {
  name: 'Login',
  emits: ['loginSuccess', 'showRegister'],
  data() {
    return {
      form: {
        email: '',
        password: ''
      },
      errors: [],
      loggingIn: false
    }
  },
  methods: {
    async handleLogin() {
      this.errors = []
      this.loggingIn = true

      try {
        const response = await api.post('/auth/login', this.form)
        
        if (response.data && response.data.user) {
          // Salvar usuário no localStorage
          auth.setUser(response.data.user)
          this.$emit('loginSuccess', response.data.user)
        } else {
          this.errors = ['Erro ao fazer login. Tente novamente.']
        }
      } catch (error) {
        console.error('Erro ao fazer login:', error)
        
        if (error.response?.data?.error) {
          this.errors = [error.response.data.error]
        } else {
          this.errors = ['Erro ao fazer login. Verifique suas credenciais.']
        }
      } finally {
        this.loggingIn = false
      }
    }
  }
}
</script>

