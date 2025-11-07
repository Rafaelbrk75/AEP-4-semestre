# SAVETOPEOPLE

Plataforma para conectar imigrantes, ONGs e oportunidades de trabalho.

Stack:
- Frontend: Vue 3 (Vite)
- Backend: Java (Spring Boot)
- Banco: MySQL (via Docker Compose)

Objetivo deste scaffold:
- Fornecer um MVP funcional com endpoints REST e front básico.
- Demonstrar conceitos de Sistemas Operacionais (segurança, processos via container) e engenharia de requisitos (MVP, stakeholders).

Como rodar (com Docker Desktop):
1. Na raiz do projeto rode:

   docker-compose up --build

2. Backend: http://localhost:8080
3. Frontend (dev): http://localhost:5173

Notas:
- Ajuste variáveis de ambiente em `docker-compose.yml` caso necessário.
- Este é um scaffold inicial. Para produção avalie usar secret management, TLS e políticas de backup para o MySQL.

Como o projeto aplica conceitos de Sistemas Operacionais e Engenharia de Requisitos
----------------------------------------------------------------
- Sistemas Operacionais: a implantação prevista em servidor Linux e o uso de containers (Docker) permite aplicar gerenciamento de processos (cada serviço roda isolado em um contêiner), políticas de segurança (usuários, portas fechadas, TLS), e persistência (volumes para o MySQL). No servidor de produção recomenda-se usar systemd ou orquestradores (Kubernetes) para gerenciar reinícios, limites de recursos (cgroups) e logs.
- Engenharia de Requisitos: o desenvolvimento segue uma abordagem de MVP. Requisitos foram coletados e mapeados em: atores (beneficiários, doadores, ONGs), casos de uso (cadastrar, listar vagas, solicitar doação, doar), requisitos funcionais mínimos para o MVP (autenticação opcional no futuro, CRUD de vagas/ONGs/doações) e não-funcionais (segurança, disponibilidade, desempenho). Testes iniciais e piloto com ONGs locais ajudarão a validar e priorizar melhorias.

Próximos passos recomendados:
- Adicionar autenticação (JWT) para controlar ações sensíveis.
- Implementar fila (RabbitMQ) para processamento assíncrono de entregas de cestas (usa conceitos de escalonamento e processos do SO).
- Monitoramento (Prometheus/Grafana) e backups automáticos do MySQL.