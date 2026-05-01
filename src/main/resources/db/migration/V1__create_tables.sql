CREATE TABLE livro (
                       id_livro BIGSERIAL PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       autor VARCHAR(100) NOT NULL,
                       data_lancamento DATE
);

CREATE TABLE pessoa (
                        id_pessoa BIGSERIAL PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL,
                        cpf VARCHAR(11) NOT NULL UNIQUE,
                        cep VARCHAR(9),
                        email VARCHAR(100) NOT NULL UNIQUE,
                        senha VARCHAR(100) NOT NULL
);

CREATE TABLE emprestimo (
                            id_livro BIGINT NOT NULL,
                            id_pessoa BIGINT NOT NULL,
                            PRIMARY KEY (id_livro, id_pessoa),
                            CONSTRAINT fk_livro FOREIGN KEY (id_livro) REFERENCES livro(id_livro) ON DELETE CASCADE,
                            CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa) ON DELETE CASCADE
);