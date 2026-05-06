package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Livro;

import java.time.LocalDate;

public record LivroResponse (
        Long idLivro,
        String nome,
        String autor,
        LocalDate dataLancamento
) {
    public LivroResponse(Livro livro){
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getDataLancamento());
    }
}
