# 🚀 Hospedar Tudo no Railway - Guia Completo

Este guia mostra como hospedar **frontend, backend e banco de dados** tudo no Railway.

---

## 📋 Pré-requisitos

- Conta no GitHub (com o código commitado)
- Conta no Railway (gratuita para começar)

---

## 🎯 Passo 1: Criar Conta no Railway

1. Acesse: https://railway.app
2. Clique em **"Login"** e faça login com GitHub
3. Autorize o Railway a acessar seus repositórios

---

## 🎯 Passo 2: Criar Novo Projeto

1. No dashboard do Railway, clique em **"New Project"**
2. Selecione **"Deploy from GitHub repo"**
3. Escolha o repositório `aep` (ou o nome do seu repositório)
4. Clique em **"Deploy Now"**

---

## 🎯 Passo 3: Adicionar Banco de Dados MySQL

1. No projeto criado, clique em **"+ New"**
2. Selecione **"Database"** → **"Add MySQL"**
3. Railway criará automaticamente um banco MySQL
4. **IMPORTANTE**: Anote as credenciais que aparecerão:
   - **MYSQLHOST** (host do banco)
   - **MYSQLUSER** (usuário, geralmente `root`)
   - **MYSQLPASSWORD** (senha)
   - **MYSQLDATABASE** (nome do banco, geralmente `railway`)

---

## 🎯 Passo 4: Deploy do Backend

1. No projeto, clique em **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório
3. Railway detectará automaticamente o código
4. Configure o serviço:
   - Clique no serviço recém-criado
   - Vá em **"Settings"**
   - Configure:
     - **Root Directory**: `backend`
     - **Dockerfile Path**: `Dockerfile` (ou deixe em branco se usar o padrão)
5. Configure as **variáveis de ambiente**:
   - Clique em **"Variables"**
   - Adicione as seguintes variáveis:
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://[MYSQLHOST]:3306/[MYSQLDATABASE]?useSSL=true&serverTimezone=UTC
     SPRING_DATASOURCE_USERNAME=[MYSQLUSER]
     SPRING_DATASOURCE_PASSWORD=[MYSQLPASSWORD]
     ```
   - **Substitua** `[MYSQLHOST]`, `[MYSQLDATABASE]`, `[MYSQLUSER]` e `[MYSQLPASSWORD]` pelos valores do banco criado no Passo 3
   - **Exemplo real**:
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://containers-us-west-123.railway.app:3306/railway?useSSL=true&serverTimezone=UTC
     SPRING_DATASOURCE_USERNAME=root
     SPRING_DATASOURCE_PASSWORD=abc123xyz
     ```
6. Railway fará o deploy automaticamente
7. Aguarde alguns minutos e verifique os logs
8. **Anote a URL do backend** (aparecerá em "Settings" → "Networking" → "Generate Domain")
   - Exemplo: `https://backend-production.up.railway.app`

---

## 🎯 Passo 5: Deploy do Frontend

1. No projeto, clique em **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório novamente
3. Configure o serviço:
   - Clique no serviço recém-criado
   - Vá em **"Settings"**
   - Configure:
     - **Root Directory**: `frontend`
     - **Dockerfile Path**: `Dockerfile.prod`
4. Configure as **variáveis de ambiente**:
   - Clique em **"Variables"**
   - Adicione a variável:
     ```
     VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
     ```
   - **Substitua** `[URL_DO_BACKEND]` pela URL do backend anotada no Passo 4
   - **IMPORTANTE**: Use a URL completa com `https://`, mas **SEM** `/api` no final
   - **Exemplo real**:
     ```
     VITE_PROXY_TARGET=https://backend-production.up.railway.app
     ```
5. Railway fará o deploy automaticamente
6. Aguarde alguns minutos
7. **Anote a URL do frontend** (aparecerá em "Settings" → "Networking" → "Generate Domain")
   - Exemplo: `https://frontend-production.up.railway.app`

---

## 🎯 Passo 6: Verificar se Está Funcionando

1. Acesse a URL do frontend no navegador
2. Teste as funcionalidades:
   - Ver ONGs
   - Ver Oportunidades
   - Aplicar para vagas
   - Fazer doações
3. Se algo não funcionar, verifique os logs:
   - Clique no serviço → **"Deployments"** → **"View Logs"**

---

## 🔧 Configurações Importantes

### Variáveis de Ambiente do Backend:
```
SPRING_DATASOURCE_URL=jdbc:mysql://[HOST]:3306/[DATABASE]?useSSL=true&serverTimezone=UTC
SPRING_DATASOURCE_USERNAME=[USER]
SPRING_DATASOURCE_PASSWORD=[PASSWORD]
```

### Variáveis de Ambiente do Frontend:
```
VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
```

### Estrutura no Railway:
```
Projeto SaveToPeople
├── MySQL Database (banco de dados)
├── Backend Service (Spring Boot)
└── Frontend Service (Vue.js + Nginx)
```

---

## 🆘 Problemas Comuns e Soluções

### ❌ Backend não conecta ao banco de dados
**Solução:**
- Verifique se as credenciais do MySQL estão corretas
- Verifique se a URL está no formato correto
- Verifique os logs do backend para ver o erro específico

### ❌ Frontend não encontra o backend (erro 404 ou CORS)
**Solução:**
- Verifique se `VITE_PROXY_TARGET` está com a URL correta do backend
- Verifique se a URL não tem `/api` no final
- Verifique se o backend está rodando (veja os logs)
- Verifique se o CORS está configurado no backend (já está com `allowedOrigins("*")`)

### ❌ Build do frontend falha
**Solução:**
- Verifique se o `Root Directory` está como `frontend`
- Verifique se o `Dockerfile Path` está como `Dockerfile.prod`
- Verifique os logs de build para ver o erro específico

### ❌ Erro 500 no backend
**Solução:**
- Verifique os logs do backend
- Verifique se o banco de dados está acessível
- Verifique se as tabelas foram criadas (o Spring Boot cria automaticamente)

---

## 📝 Checklist Final

- [ ] Conta criada no Railway
- [ ] Projeto criado e conectado ao GitHub
- [ ] Banco de dados MySQL criado
- [ ] Credenciais do banco anotadas
- [ ] Backend deployado com variáveis de ambiente configuradas
- [ ] URL do backend anotada
- [ ] Frontend deployado com variável `VITE_PROXY_TARGET` configurada
- [ ] URLs públicas geradas
- [ ] Aplicação testada e funcionando

---

## 💰 Custos

- **Railway**: 
  - Plano gratuito: $5 de crédito grátis por mês
  - Após isso: ~$5-20/mês dependendo do uso
  - Você pode monitorar o uso no dashboard

---

## 🎉 Pronto!

Agora sua aplicação está online e acessível publicamente!

- **Frontend**: `https://frontend-production.up.railway.app`
- **Backend**: `https://backend-production.up.railway.app`
- **API**: `https://backend-production.up.railway.app/api`

---

## 📚 Próximos Passos (Opcional)

1. **Configurar domínio personalizado**:
   - No Railway, vá em "Settings" → "Networking" → "Custom Domain"
   - Adicione seu domínio

2. **Monitorar uso**:
   - Acompanhe o uso de recursos no dashboard do Railway

3. **Configurar CI/CD**:
   - O Railway já faz deploy automático quando você faz push no GitHub

---

**Precisa de ajuda? Me avise qual passo está com dificuldade!**

