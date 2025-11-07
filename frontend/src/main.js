import { createApp } from 'vue'
import App from './App.vue'
import './styles.css'

// Aplicar dark mode antes de montar o app para evitar flash
const saved = localStorage.getItem('darkMode')
if (saved === 'true') {
  document.documentElement.classList.add('dark')
} else if (saved === null) {
  // Se não há preferência salva, detectar do sistema
  if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
    document.documentElement.classList.add('dark')
  }
}

createApp(App).mount('#app')
