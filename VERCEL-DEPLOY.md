# 🚀 Hospedar Frontend na Vercel + Backend no Railway

Como a Vercel não suporta Java/Spring Boot, vamos hospedar:
- **Frontend**: Vercel (gratuito e rápido)
- **Backend**: Railway (suporta Java/Spring Boot)

---

## 📋 Passo 1: Deploy do Backend no Railway

### 1.1 Criar Conta no Railway
- Acesse: https://railway.app
- Faça login com GitHub

### 1.2 Criar Projeto
1. Clique em **"New Project"**
2. Selecione **"Deploy from GitHub repo"**
3. Escolha seu repositório

### 1.3 Adicionar MySQL
1. Clique em **"+ New"** → **"Database"** → **"Add MySQL"**
2. **Anote as credenciais** (host, usuário, senha)

### 1.4 Deploy do Backend
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

---

## 📋 Passo 2: Deploy do Frontend na Vercel

### 2.1 Criar Conta na Vercel
- Acesse: https://vercel.com
- Faça login com GitHub

### 2.2 Importar Projeto
1. Clique em **"Add New"** → **"Project"**
2. Importe seu repositório do GitHub
3. A Vercel detectará automaticamente o projeto

### 2.3 Configurar Build
Configure os seguintes campos:
- **Framework Preset**: Other
- **Root Directory**: `frontend`
- **Build Command**: `npm run build`
- **Output Directory**: `dist`
- **Install Command**: `npm install`

### 2.4 Variáveis de Ambiente
Adicione a variável:
```
VITE_API_URL=https://[URL_DO_BACKEND]/api
```
- Substitua `[URL_DO_BACKEND]` pela URL do backend do Railway
- **Importante**: Use `/api` no final da URL

### 2.5 Deploy
1. Clique em **"Deploy"**
2. Aguarde alguns minutos
3. A Vercel gerará uma URL pública (ex: `https://seu-projeto.vercel.app`)

---

## 📋 Passo 3: Configurar CORS no Backend

O backend precisa permitir requisições do frontend da Vercel.

### 3.1 Atualizar WebConfig.java
O arquivo `WebConfig.java` já está configurado com `allowedOrigins("*")`, mas para produção é melhor especificar o domínio:

```java
registry.addMapping("/api/**")
    .allowedOrigins("https://seu-projeto.vercel.app", "http://localhost:5173")
    .allowedMethods("GET","POST","PUT","DELETE","OPTIONS");
```

Ou mantenha `"*"` se quiser permitir qualquer origem (menos seguro, mas funciona).

---

## ✅ Checklist

- [ ] Backend deployado no Railway
- [ ] URL do backend anotada
- [ ] Frontend deployado na Vercel
- [ ] Variável `VITE_API_URL` configurada na Vercel
- [ ] CORS configurado no backend
- [ ] Testar aplicação online

---

## 🎯 URLs Finais

- **Frontend**: `https://seu-projeto.vercel.app`
- **Backend**: `https://backend-production.up.railway.app`
- **API**: `https://backend-production.up.railway.app/api`

---

## 💰 Custos

- **Vercel**: Gratuito (plano generoso)
- **Railway**: ~$5-20/mês (ou plano gratuito para testes)

---

## 🆘 Problemas Comuns

### Frontend não encontra o backend:
- Verifique se `VITE_API_URL` está configurada corretamente na Vercel
- Verifique se a URL do backend está acessível publicamente
- Verifique os logs na Vercel (Build Logs)

### Erro de CORS:
- Verifique se o `WebConfig.java` permite a origem do frontend
- Verifique se o backend está rodando

### Build falha na Vercel:
- Verifique se o `Root Directory` está como `frontend`
- Verifique se o `Output Directory` está como `dist`
- Verifique os logs de build na Vercel

---

**Pronto! Agora você tem o frontend na Vercel e o backend no Railway! 🎉**

