-- Dados iniciais para demonstração (MVP)
INSERT INTO ongs (id, name, contact, description) VALUES (1, 'ONG Solidaria', 'contato@solidaria.org', 'Apoio a imigrantes e distribuição de cestas');
INSERT INTO ongs (id, name, contact, description) VALUES (2, 'Acolhe Bem', 'contato@acolhebem.org', 'Acolhimento e capacitação para inserção no mercado de trabalho');

INSERT INTO jobs (id, title, description, location) VALUES (1, 'Auxiliar de Cozinha', 'Vaga para auxiliar de cozinha em restaurante social', 'São Paulo - SP');
INSERT INTO jobs (id, title, description, location) VALUES (2, 'Atendente', 'Atendimento ao público em ONG parceira', 'Rio de Janeiro - RJ');

INSERT INTO users (id, name, email, address, role) VALUES (1, 'Maria Beneficiaria', 'maria@example.com', 'Rua A, 123', 'beneficiary');
INSERT INTO users (id, name, email, address, role) VALUES (2, 'Joao Doador', 'joao@example.com', 'Av B, 456', 'donor');

-- Exemplo de doação
INSERT INTO donations (id, donor_id, beneficiary_id, items, status) VALUES (1, 2, 1, 'Cesta basica (arroz, feijao, óleo, macarrão)', 'sent');
