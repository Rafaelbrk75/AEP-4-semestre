# Guia de Deploy - SaveToPeople

Este guia explica como hospedar a aplicação SaveToPeople em diferentes plataformas.

## Opções de Hospedagem

### 1. Railway (Recomendado - Mais Fácil)

Railway é a opção mais simples para deploy com Docker.

#### Passos:

1. **Criar conta no Railway**: https://railway.app
2. **Conectar repositório GitHub**:
   - No Railway, clique em "New Project"
   - Selecione "Deploy from GitHub repo"
   - Escolha seu repositório

3. **Configurar serviços**:
   - Railway detectará automaticamente o `docker-compose.yml`
   - Ou você pode usar o `Dockerfile.prod` para build único

4. **Variáveis de ambiente**:
   - Adicione as variáveis necessárias no painel do Railway:
     - `SPRING_DATASOURCE_URL` (Railway fornecerá automaticamente se usar o serviço MySQL)
     - `SPRING_DATASOURCE_USERNAME`
     - `SPRING_DATASOURCE_PASSWORD`
     - `VITE_PROXY_TARGET` (URL do backend)

5. **Banco de dados**:
   - Adicione um serviço MySQL no Railway
   - Railway gerará automaticamente as variáveis de conexão

#### Custo: ~$5-20/mês (dependendo do uso)

---

### 2. Render

Render é similar ao Railway, com plano gratuito limitado.

#### Passos:

1. **Criar conta**: https://render.com
2. **Criar serviços**:
   - **Web Service** para o backend (usando `Dockerfile.prod`)
   - **Web Service** para o frontend (usando `frontend/Dockerfile.prod`)
   - **PostgreSQL** (ou MySQL) para o banco de dados

3. **Configurar variáveis de ambiente** no painel do Render

4. **Deploy automático** a partir do GitHub

#### Custo: Plano gratuito disponível (com limitações)

---

### 3. Vercel (Frontend) + Railway/Render (Backend)

Separação: Frontend no Vercel, Backend em outra plataforma.

#### Frontend no Vercel:

1. **Criar conta**: https://vercel.com
2. **Importar projeto** do GitHub
3. **Configurar build**:
   - Build Command: `npm run build`
   - Output Directory: `dist`
   - Install Command: `npm install`

4. **Variáveis de ambiente**:
   - `VITE_PROXY_TARGET`: URL do seu backend (ex: https://seu-backend.railway.app)

#### Backend no Railway/Render:
- Siga os passos da opção 1 ou 2 acima

---

## Configuração do Banco de Dados

### Para produção, você precisará:

1. **Criar banco de dados** na plataforma escolhida
2. **Atualizar variáveis de ambiente**:
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://seu-host:3306/savetpeople?useSSL=true&serverTimezone=UTC
   SPRING_DATASOURCE_USERNAME=seu_usuario
   SPRING_DATASOURCE_PASSWORD=sua_senha
   ```

3. **Executar migrations** (o Spring Boot criará as tabelas automaticamente com `ddl-auto=update`)

---

## Checklist de Deploy

- [ ] Código commitado no GitHub
- [ ] `.gitignore` configurado (node_modules não deve ser commitado)
- [ ] Variáveis de ambiente configuradas
- [ ] Banco de dados criado
- [ ] Domínio configurado (opcional)
- [ ] SSL/HTTPS habilitado (geralmente automático)

---

## Suporte

Se precisar de ajuda com o deploy, me avise qual plataforma você escolheu!

