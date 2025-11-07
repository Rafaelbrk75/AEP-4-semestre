/** @type {import('tailwindcss').Config} */
// Tailwind v4 - configuração mínima necessária
// Cores personalizadas estão definidas em styles.css usando @theme
module.exports = {
  darkMode: 'class', // Habilita dark mode usando classe
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
}

