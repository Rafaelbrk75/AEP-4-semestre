-- Garantir que o usuário appuser possa conectar de qualquer host
-- O usuário já foi criado pelo docker-entrypoint, apenas damos permissões
GRANT ALL PRIVILEGES ON savetpeople.* TO 'appuser'@'%';
FLUSH PRIVILEGES;

