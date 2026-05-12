package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Pessoa;


public record PessoaResponseSimple (
        Long idPessoa,
        String nome
) {
    public PessoaResponseSimple(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getNome());
    }
}
