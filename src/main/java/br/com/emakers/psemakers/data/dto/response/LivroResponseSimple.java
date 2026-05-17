package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Livro;

public record LivroResponseSimple (
    Long idLivro,
    String nome,
    String autor
) {
    public LivroResponseSimple(Livro livro){
        this(
                livro.getIdLivro(), livro.getNome(), livro.getAutor()
        );
    }
}
