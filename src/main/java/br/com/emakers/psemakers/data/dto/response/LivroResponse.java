package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Livro;

import java.time.LocalDate;
import java.util.List;

public record LivroResponse (
        Long idLivro,
        String nome,
        String autor,
        LocalDate dataLancamento,
        List<PessoaResponseSimple> pessoas
) {
    public LivroResponse(Livro livro){
        this(livro.getIdLivro(), livro.getNome(), livro.getAutor(), livro.getDataLancamento(),
                livro.getPessoas() != null ?
                        livro.getPessoas().stream().map(PessoaResponseSimple::new).toList() : java.util.Collections.emptyList());
    }
}
