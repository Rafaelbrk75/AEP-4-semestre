import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    host: '0.0.0.0',
    port: 5173,
    strictPort: false,
    hmr: {
      port: 5175, // Porta do host quando rodando no Docker
      host: 'localhost',
      protocol: 'ws'
    },
    watch: {
      usePolling: true
    },
    proxy: {
      '/api': {
        // Para desenvolvimento local: http://localhost:8080
        // Para Docker: http://backend:8080 (service name do docker-compose)
        target: process.env.VITE_PROXY_TARGET || 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path
      }
    }
  }
})
