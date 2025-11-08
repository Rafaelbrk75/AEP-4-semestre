// Sistema de autenticação
const AUTH_KEY = 'savetpeople_user'

export const auth = {
  // Salvar usuário logado
  setUser(user) {
    localStorage.setItem(AUTH_KEY, JSON.stringify(user))
  },

  // Obter usuário logado
  getUser() {
    const userStr = localStorage.getItem(AUTH_KEY)
    if (!userStr) return null
    try {
      return JSON.parse(userStr)
    } catch {
      return null
    }
  },

  // Verificar se está autenticado
  isAuthenticated() {
    return this.getUser() !== null
  },

  // Verificar role do usuário
  hasRole(role) {
    const user = this.getUser()
    return user && user.role === role
  },

  // Verificar se é doador ou ONG
  isDonorOrNGO() {
    const user = this.getUser()
    return user && (user.role === 'donor' || user.role === 'ngo')
  },

  // Verificar se é beneficiário
  isBeneficiary() {
    const user = this.getUser()
    return user && user.role === 'beneficiary'
  },

  // Verificar se é administrador
  isAdmin() {
    const user = this.getUser()
    return user && user.role === 'admin'
  },

  // Fazer logout
  logout() {
    localStorage.removeItem(AUTH_KEY)
  }
}

