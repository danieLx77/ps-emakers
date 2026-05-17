ALTER TABLE pessoa
    ADD COLUMN status VARCHAR(20) DEFAULT 'ATIVO' NOT NULL;

ALTER TABLE livro
    ADD COLUMN status VARCHAR(20) DEFAULT 'ATIVO' NOT NULL;

ALTER TABLE emprestimo
    DROP CONSTRAINT fk_pessoa,
    DROP CONSTRAINT fk_livro;

ALTER TABLE emprestimo
    ADD CONSTRAINT fk_pessoa FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),
    ADD CONSTRAINT fk_livro FOREIGN KEY (id_livro) REFERENCES livro(id_livro);