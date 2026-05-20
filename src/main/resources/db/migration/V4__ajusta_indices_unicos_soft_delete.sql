ALTER TABLE pessoa DROP CONSTRAINT IF EXISTS pessoa_cpf_key;
ALTER TABLE pessoa DROP CONSTRAINT IF EXISTS pessoa_email_key;

CREATE UNIQUE INDEX idx_pessoa_cpf_ativo ON pessoa(cpf) WHERE status = 'ATIVO';
CREATE UNIQUE INDEX idx_pessoa_email_ativo ON pessoa(email) WHERE status = 'ATIVO';