# 🚀 Como Hospedar a Aplicação SaveToPeople

Este guia mostra como hospedar sua aplicação passo a passo.

## 📋 Pré-requisitos

- Conta no GitHub (com o código commitado)
- Conta em uma plataforma de hospedagem (recomendamos Railway)

---

## 🎯 Opção 1: Railway (Recomendado - Mais Fácil)

Railway é a opção mais simples e suporta Docker diretamente.

### Passo 1: Criar Conta
1. Acesse: https://railway.app
2. Clique em "Login" e faça login com GitHub
3. Autorize o Railway a acessar seus repositórios

### Passo 2: Criar Novo Projeto
1. No dashboard do Railway, clique em **"New Project"**
2. Selecione **"Deploy from GitHub repo"**
3. Escolha o repositório `aep` (ou o nome do seu repositório)

### Passo 3: Adicionar Banco de Dados MySQL
1. No projeto criado, clique em **"+ New"**
2. Selecione **"Database"** → **"Add MySQL"**
3. Railway criará automaticamente um banco MySQL
4. **Anote as credenciais** que aparecerão (você precisará delas)

### Passo 4: Configurar Backend
1. No projeto, clique em **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório
3. Railway detectará o `docker-compose.yml` ou você pode usar o `Dockerfile.prod`
4. Configure as **variáveis de ambiente**:
   - Clique no serviço do backend
   - Vá em **"Variables"**
   - Adicione:
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://[HOST_DO_MYSQL]:3306/railway?useSSL=true&serverTimezone=UTC
     SPRING_DATASOURCE_USERNAME=root
     SPRING_DATASOURCE_PASSWORD=[SENHA_DO_MYSQL]
     ```
   - Substitua `[HOST_DO_MYSQL]` e `[SENHA_DO_MYSQL]` pelos valores do banco criado
5. Configure a porta: Railway usará automaticamente a porta 8080

### Passo 5: Configurar Frontend
1. Adicione outro serviço: **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório
3. Configure o **Root Directory** como `frontend`
4. Configure o **Dockerfile Path** como `Dockerfile.prod`
5. Adicione variável de ambiente:
   ```
   VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
   ```
   - Substitua `[URL_DO_BACKEND]` pela URL do backend (ex: `https://backend-production.up.railway.app`)

### Passo 6: Deploy
1. Railway fará o deploy automaticamente
2. Aguarde alguns minutos
3. Railway gerará URLs públicas para cada serviço

### Passo 7: Configurar Domínio (Opcional)
1. No serviço, vá em **"Settings"** → **"Domains"**
2. Adicione seu domínio personalizado

---

## 🎯 Opção 2: Render (Plano Gratuito Disponível)

### Passo 1: Criar Conta
1. Acesse: https://render.com
2. Faça login com GitHub

### Passo 2: Criar Banco de Dados
1. Clique em **"New +"** → **"PostgreSQL"** (ou MySQL se disponível)
2. Escolha o plano (Free tier disponível)
3. Anote as credenciais de conexão

### Passo 3: Deploy do Backend
1. Clique em **"New +"** → **"Web Service"**
2. Conecte seu repositório GitHub
3. Configure:
   - **Name**: `save-to-people-backend`
   - **Root Directory**: `backend`
   - **Environment**: `Docker`
   - **Dockerfile Path**: `Dockerfile`
4. Adicione variáveis de ambiente:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://[HOST]:3306/[DATABASE]?useSSL=true
   SPRING_DATASOURCE_USERNAME=[USER]
   SPRING_DATASOURCE_PASSWORD=[PASSWORD]
   ```
5. Clique em **"Create Web Service"**

### Passo 4: Deploy do Frontend
1. Clique em **"New +"** → **"Web Service"**
2. Conecte o mesmo repositório
3. Configure:
   - **Name**: `save-to-people-frontend`
   - **Root Directory**: `frontend`
   - **Environment**: `Docker`
   - **Dockerfile Path**: `Dockerfile.prod`
4. Adicione variável de ambiente:
   ```
   VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
   ```
5. Clique em **"Create Web Service"**

---

## 🎯 Opção 3: Vercel (Frontend) + Railway (Backend)

### Frontend no Vercel:
1. Acesse: https://vercel.com
2. Faça login com GitHub
3. Clique em **"Add New"** → **"Project"**
4. Importe seu repositório
5. Configure:
   - **Framework Preset**: Vite
   - **Root Directory**: `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`
6. Adicione variável de ambiente:
   ```
   VITE_API_URL=https://[URL_DO_BACKEND]/api
   ```
7. Clique em **"Deploy"**

### Backend no Railway:
- Siga os passos da Opção 1 (Passos 3-4)

---

## ⚙️ Configurações Importantes

### Variáveis de Ambiente do Backend:
```
SPRING_DATASOURCE_URL=jdbc:mysql://host:port/database?useSSL=true&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=senha
```

### Variáveis de Ambiente do Frontend:
```
VITE_API_URL=https://seu-backend.com/api
```
ou
```
VITE_PROXY_TARGET=https://seu-backend.com
```

---

## 🔧 Ajustes Necessários para Produção

### 1. Atualizar `application.properties`:
```properties
# Desabilitar logs SQL em produção
spring.jpa.show-sql=false

# Usar SSL no banco de dados
spring.datasource.url=jdbc:mysql://...?useSSL=true
```

### 2. Configurar CORS no Backend:
O `WebConfig.java` já deve estar configurado, mas verifique se permite o domínio do frontend.

---

## 📝 Checklist Final

- [ ] Código commitado no GitHub
- [ ] `.gitignore` configurado (node_modules não commitado)
- [ ] Banco de dados criado
- [ ] Variáveis de ambiente configuradas
- [ ] Backend deployado e funcionando
- [ ] Frontend deployado e funcionando
- [ ] Testar aplicação online
- [ ] Configurar domínio personalizado (opcional)

---

## 🆘 Problemas Comuns

### Backend não conecta ao banco:
- Verifique as credenciais do banco
- Verifique se o banco aceita conexões externas
- Verifique se a URL está correta (com porta)

### Frontend não encontra o backend:
- Verifique a variável `VITE_API_URL` ou `VITE_PROXY_TARGET`
- Verifique se o backend está acessível publicamente
- Verifique CORS no backend

### Erro 404 no frontend:
- Configure o nginx para SPA (já está no `nginx.conf`)
- Verifique se os arquivos foram buildados corretamente

---

## 💰 Custos Estimados

- **Railway**: ~$5-20/mês (dependendo do uso)
- **Render**: Plano gratuito disponível (com limitações)
- **Vercel**: Plano gratuito generoso para frontend

---

Precisa de ajuda com algum passo específico? Me avise qual plataforma você escolheu!

