# 🫱 SaveToPeople

> Conectando imigrantes, ONGs e oportunidades de trabalho.

SaveToPeople é uma plataforma criada como scaffold/MVP para aproximar pessoas em situação de vulnerabilidade, organizações sociais e empregadores — promovendo inclusão e oportunidades reais por meio de uma stack moderna (Vue 3 + Spring Boot) orquestrada com Docker.

Demo / Deploy
- Projeto online (deploy): https://v0-aep-4semestremain.vercel.app/

---

## 🧩 Stack Tecnológica

| Camada            | Tecnologia                                                  |
|-------------------|-------------------------------------------------------------|
| 🖥️ Frontend       | Vue 3 (Vite)                                                |
| ⚙️ Backend        | Java (Spring Boot)                                          |
| 🗄️ Banco de Dados | MySQL (via Docker Compose)                                  |
| 🐳 Infraestrutura | Docker + Docker Compose                                     |

(Linguagens no repositório: Vue ~72.6%, Java ~23.2%, JavaScript ~2.6%)

---

## 🎯 Objetivos do Projeto

- Entregar um **MVP funcional** com API REST e frontend responsivo.  
- Permitir experimentação de conceitos de Sistemas Operacionais (containers, isolamento, processos).  
- Aplicar práticas de Engenharia de Requisitos (atores, casos de uso, validação de funcionalidades essenciais).

---

## 📂 Estrutura principal do repositório

- /frontend — aplicação Vue 3 (Vite)  
- /backend — API Java Spring Boot  
- /mysql-init — scripts de inicialização do banco  
- docker-compose.yml — orquestração (frontend, backend, db)  
- Dockerfile.prod, Dockerfile.prod.backup — imagens de produção  
- Guías de deploy: README-DEPLOY.md, DEPLOY.md, RAILWAY-TUDO.md, VERCEL-DEPLOY.md  
- render.yaml, vercel.json — configurações para provedores

---

## ⚙️ Como executar (recomendado: Docker)

Pré-requisito: Docker Desktop (ou Docker Engine + Compose).

1. Na raiz do projeto, suba os serviços:

```bash
git clone https://github.com/Rafaelbrk75/AEP-4-semestre.git
cd AEP-4-semestre
docker-compose up --build
```

2. Endpoints/URLs padrão:
- Frontend (Vite dev): http://localhost:5173
- Backend (Spring Boot): http://localhost:8080
- MySQL: 3306 (conforme docker-compose)

Para encerrar:
```bash
docker-compose down
```

Se quiser usar um arquivo de variáveis:
```bash
docker-compose --env-file .env up --build
```

---

## 🧰 Rodando localmente (desenvolvimento separado)

Backend (Maven):
```bash
cd backend
# ajuste application.properties ou .env conforme necessário
mvn clean spring-boot:run
```

ou com Gradle (se existir):
```bash
./gradlew bootRun
```

Frontend (Vite):
```bash
cd frontend
npm install
npm run dev
# ou
yarn
yarn dev
```

Banco de dados:
- Você pode usar o serviço MySQL do docker-compose (recomendado para replicar ambiente).
- Caso rode localmente outra instância, ajuste SPRING_DATASOURCE_URL no backend.

---

## 🧩 Variáveis de ambiente (sugestão / .env.example)

Exemplos (não commitar secrets):

backend (.env ou application.properties):
```
SPRING_PROFILES_ACTIVE=local
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/savetopeople
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=rootpassword
JWT_SECRET=troque_por_uma_chave_secreta
```

frontend (.env):
```
VITE_API_URL=http://localhost:8080/api
```

---

## 🔌 Endpoints principais (exemplos)

A API do scaffold segue padrão REST. Ajuste conforme o código real em /backend:

- POST /api/auth/login — autenticação
- POST /api/auth/register — cadastro de usuário
- GET /api/people — listar pessoas
- POST /api/people — cadastrar pessoa em vulnerabilidade
- GET /api/jobs — listar vagas
- POST /api/jobs — publicar vaga

Teste com Postman ou curl, por exemplo:
```bash
curl -X GET http://localhost:8080/api/people
```

---

## ✅ Testes

- Backend:
  - mvn test
  - ou ./gradlew test (se Gradle estiver configurado)
- Frontend:
  - npm run test (Jest/Vitest, conforme configuração)
  - yarn test

Adicione cobertura e pipelines CI conforme necessário.

---

## 📦 Build para produção

Frontend:
```bash
cd frontend
npm run build
# saída em dist/
```

Backend:
```bash
mvn clean package
# ou ./gradlew bootJar
# gera jar em target/ ou build/libs/
```

Há um Dockerfile.prod e orientações de deploy nos arquivos README-DEPLOY.md / DEPLOY.md / RAILWAY-TUDO.md para publicar em provedores (Railway, Vercel, Render etc).

---

## 🔒 Boas práticas de segurança

- Nunca commitar chaves/segredos. Use secrets do provedor ou variáveis de ambiente.
- Valide e sanitize input no backend.
- Habilite HTTPS em produção e CORS restrito.
- Configure backups automáticos para o MySQL.

---

## 🤝 Como contribuir

1. Fork -> clone -> branch: git checkout -b feat/nome-da-feature  
2. Código limpo, commits atômicos e PR com descrição clara  
3. Execute testes e linters antes de submeter  
4. Use issues para propor melhorias/bugs

Sugestões de features: autenticação com verificação de documentos, dashboard para ONGs, i18n (português/inglês/espanhol), integração com provedores de vagas.

---

## 🧾 Licença e contato

- Licença: MIT (adicionar arquivo LICENSE se necessário)  
- Mantenedor: Rafaelbrk75 — https://github.com/Rafaelbrk75

---

## 🔎 Onde olhar no repositório

- docker-compose.yml — orquestração  
- frontend/ — app Vue (Vite)  
- backend/ — API Spring Boot  
- mysql-init/ — scripts de inicialização do banco  
- README-DEPLOY.md, DEPLOY.md, RAILWAY-TUDO.md, VERCEL-DEPLOY.md — guias de deploy  
- Dockerfile.prod — imagem de produção

---

Obrigado — este README foi adaptado ao repositório AEP-4-semestre e agora inclui o link do deploy público.  
