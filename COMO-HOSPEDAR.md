# 🚀 Como Hospedar - Guia Rápido

## Opção Mais Fácil: Railway

### 1️⃣ Criar Conta
- Acesse: https://railway.app
- Faça login com GitHub

### 2️⃣ Criar Projeto
1. Clique em **"New Project"**
2. Selecione **"Deploy from GitHub repo"**
3. Escolha seu repositório

### 3️⃣ Adicionar MySQL
1. Clique em **"+ New"** → **"Database"** → **"Add MySQL"**
2. **Anote as credenciais** (aparecem na tela)

### 4️⃣ Deploy do Backend
1. Clique em **"+ New"** → **"GitHub Repo"** (mesmo repositório)
2. Configure:
   - **Root Directory**: `backend`
   - **Dockerfile Path**: `Dockerfile`
3. Vá em **"Variables"** e adicione:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://[HOST]:3306/railway?useSSL=true&serverTimezone=UTC
   SPRING_DATASOURCE_USERNAME=root
   SPRING_DATASOURCE_PASSWORD=[SENHA]
   ```
   - Substitua `[HOST]` e `[SENHA]` pelos valores do MySQL
4. Railway fará o deploy automaticamente
5. **Anote a URL do backend** (ex: `https://backend-production.up.railway.app`)

### 5️⃣ Deploy do Frontend
1. Clique em **"+ New"** → **"GitHub Repo"** (mesmo repositório)
2. Configure:
   - **Root Directory**: `frontend`
   - **Dockerfile Path**: `Dockerfile.prod`
3. Vá em **"Variables"** e adicione:
   ```
   VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
   ```
   - Substitua `[URL_DO_BACKEND]` pela URL do backend (sem `/api` no final)
4. Railway fará o deploy automaticamente

### 6️⃣ Pronto! 🎉
- Railway gerará URLs públicas para cada serviço
- Acesse a URL do frontend e teste a aplicação

---

## 📝 Dicas Importantes

1. **URLs do Railway**: 
   - Backend: `https://backend-production.up.railway.app`
   - Frontend: `https://frontend-production.up.railway.app`

2. **Variável VITE_PROXY_TARGET**:
   - Use a URL completa do backend (com `https://`)
   - Exemplo: `https://backend-production.up.railway.app`

3. **Banco de Dados**:
   - O Railway cria automaticamente as tabelas quando o backend inicia
   - Não precisa executar scripts SQL manualmente

---

## 🆘 Problemas?

### Backend não conecta ao banco:
- Verifique se as credenciais estão corretas
- Verifique se a URL do banco está no formato correto

### Frontend não encontra o backend:
- Verifique se `VITE_PROXY_TARGET` está com a URL correta do backend
- Verifique se o backend está rodando (veja os logs no Railway)

### Erro 404:
- Aguarde alguns minutos após o deploy
- Verifique os logs no Railway

---

## 💰 Custo
- Railway: ~$5-20/mês (dependendo do uso)
- Plano gratuito disponível para testes

---

**Precisa de ajuda? Me avise qual passo está com dificuldade!**

