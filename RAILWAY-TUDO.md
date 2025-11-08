# 🚀 Hospedar Tudo no Railway - Guia Completo

Este guia mostra como hospedar **frontend, backend e banco de dados** tudo no Railway.

## ⚠️ IMPORTANTE: Como o Railway Funciona

- **NÃO use `docker-compose`** no Railway
- **Crie serviços separados** para cada parte (MySQL, Backend, Frontend)
- Cada serviço usa seu próprio Dockerfile
- O Railway gerencia a comunicação entre os serviços automaticamente

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

**NOTA**: Se o Railway tentar fazer deploy automaticamente e der erro sobre `docker-compose`, não se preocupe. Vamos criar os serviços manualmente.

---

## 🎯 Passo 3: Adicionar Banco de Dados MySQL

1. No projeto criado, clique em **"+ New"**
2. Selecione **"Database"** → **"Add MySQL"**
3. Railway criará automaticamente um banco MySQL
4. **IMPORTANTE**: Anote as credenciais que aparecerão na aba **"Variables"**:
   - **MYSQLHOST** (host do banco, ex: `containers-us-west-123.railway.app`)
   - **MYSQLUSER** (usuário, geralmente `root`)
   - **MYSQLPASSWORD** (senha gerada automaticamente)
   - **MYSQLDATABASE** (nome do banco, geralmente `railway`)
   - **MYSQLPORT** (porta, geralmente `3306`)

**Dica**: Você pode copiar essas variáveis diretamente da aba "Variables" do serviço MySQL.

---

## 🎯 Passo 4: Deploy do Backend

1. No projeto, clique em **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório
3. **IMPORTANTE**: Se o Railway tentar fazer deploy automaticamente e der erro, delete esse serviço e crie novamente
4. Configure o serviço:
   - Clique no serviço recém-criado
   - Vá em **"Settings"** (ícone de engrenagem)
   - Role até **"Source"**
   - Configure:
     - **Root Directory**: `backend`
     - **Dockerfile Path**: `Dockerfile` (ou deixe em branco - o Railway encontrará automaticamente o `backend/Dockerfile`)
   - Clique em **"Save"**
   - **VERIFIQUE** que não está usando `Dockerfile.prod` da raiz
5. Configure as **variáveis de ambiente**:
   - Clique em **"Variables"** (ou use a aba "Variables")
   - Clique em **"+ New Variable"**
     - Adicione as seguintes variáveis (uma por uma):
       ```
      SPRING_DATASOURCE_URL=jdbc:mysql://[MYSQLHOST]:3306/[MYSQLDATABASE]?useSSL=true&serverTimezone=UTC
      SPRING_DATASOURCE_USERNAME=[MYSQLUSER]
      SPRING_DATASOURCE_PASSWORD=[MYSQLPASSWORD]
       ```
     - **Substitua** `[MYSQLHOST]`, `[MYSQLDATABASE]`, `[MYSQLUSER]` e `[MYSQLPASSWORD]` pelos valores do banco criado no Passo 3
     - **IMPORTANTE**: Use `mysql.railway.internal` como host se os serviços estiverem no mesmo projeto Railway, ou use o host público do MySQL
   - **Exemplo real**:
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://containers-us-west-123.railway.app:3306/railway?useSSL=true&serverTimezone=UTC
     SPRING_DATASOURCE_USERNAME=root
     SPRING_DATASOURCE_PASSWORD=abc123xyz789
     ```
6. Railway fará o deploy automaticamente
7. Aguarde alguns minutos e verifique os logs (aba "Deployments" → "View Logs")
8. **Anote a URL do backend**:
   - Vá em **"Settings"** → **"Networking"**
   - Clique em **"Generate Domain"** (se ainda não tiver)
   - A URL aparecerá (ex: `https://backend-production.up.railway.app`)
   - **Copie essa URL** - você precisará dela para o frontend

---

## 🎯 Passo 5: Deploy do Frontend

1. No projeto, clique em **"+ New"** → **"GitHub Repo"**
2. Selecione o mesmo repositório novamente
3. Configure o serviço:
   - Clique no serviço recém-criado
   - Vá em **"Settings"** (ícone de engrenagem)
   - Role até **"Source"**
   - Configure:
     - **Root Directory**: `frontend`
     - **Dockerfile Path**: `Dockerfile.prod`
   - Clique em **"Save"**
4. Configure as **variáveis de ambiente**:
   - Clique em **"Variables"** (ou use a aba "Variables")
   - Clique em **"+ New Variable"**
   - Adicione a variável:
     ```
     VITE_PROXY_TARGET=https://[URL_DO_BACKEND]
     ```
   - **Substitua** `[URL_DO_BACKEND]` pela URL do backend anotada no Passo 4
   - **IMPORTANTE**: 
     - Use a URL completa com `https://`
     - **NÃO** coloque `/api` no final
     - **Exemplo real**:
       ```
       VITE_PROXY_TARGET=https://backend-production.up.railway.app
       ```
5. Railway fará o deploy automaticamente
6. Aguarde alguns minutos
7. **Anote a URL do frontend**:
   - Vá em **"Settings"** → **"Networking"**
   - Clique em **"Generate Domain"** (se ainda não tiver)
   - A URL aparecerá (ex: `https://frontend-production.up.railway.app`)

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

### ❌ Erro: "The executable `docker-compose` could not be found"
**Causa**: O Railway detectou o `Dockerfile.prod` na raiz ou tentou usar `docker-compose.yml`.

**Solução**:
1. **Delete o serviço** que está dando erro
2. **Verifique as configurações**:
   - Backend: Root Directory = `backend`, Dockerfile Path = `Dockerfile` (ou vazio)
   - Frontend: Root Directory = `frontend`, Dockerfile Path = `Dockerfile.prod`
3. **NÃO** deixe o Railway usar `Dockerfile.prod` da raiz
4. **NÃO** use `docker-compose.yml` no Railway
5. Crie os serviços manualmente seguindo os passos acima

### ❌ Backend não conecta ao banco de dados
**Solução**:
- Verifique se as credenciais do MySQL estão corretas
- Verifique se a URL está no formato correto (com `useSSL=true`)
- Verifique os logs do backend para ver o erro específico
- Certifique-se de que o host do MySQL está correto (não use `localhost`)

### ❌ Frontend não encontra o backend (erro 404 ou CORS)
**Solução**:
- Verifique se `VITE_PROXY_TARGET` está com a URL correta do backend
- Verifique se a URL não tem `/api` no final
- Verifique se o backend está rodando (veja os logs)
- Verifique se o CORS está configurado no backend (já está com `allowedOrigins("*")`)

### ❌ Build do frontend falha
**Solução**:
- Verifique se o `Root Directory` está como `frontend`
- Verifique se o `Dockerfile Path` está como `Dockerfile.prod`
- Verifique os logs de build para ver o erro específico
- Certifique-se de que o arquivo `frontend/Dockerfile.prod` existe

### ❌ Erro 500 no backend
**Solução**:
- Verifique os logs do backend
- Verifique se o banco de dados está acessível
- Verifique se as tabelas foram criadas (o Spring Boot cria automaticamente)
- Verifique se as variáveis de ambiente estão corretas

### ❌ Frontend mostra erro de conexão
**Solução**:
- Verifique se o backend está rodando
- Verifique se a URL do backend está correta
- Verifique se o nginx está configurado corretamente (o `nginx.conf.template` já está configurado)

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

4. **Adicionar variáveis de ambiente adicionais**:
   - Se precisar de mais configurações, adicione em "Variables"

---

## 🔄 Atualizações Futuras

Quando você fizer mudanças no código:
1. Faça commit e push no GitHub
2. O Railway detectará automaticamente as mudanças
3. Fará deploy automático dos serviços afetados
4. Aguarde alguns minutos para o deploy completar

---

**Precisa de ajuda? Me avise qual passo está com dificuldade!**
