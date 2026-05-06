package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Pessoa;

public record PessoaResponse (
        Long idPessoa,
        String nome,
        String cpf,
        String email,
        String cep
) {
    public PessoaResponse(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail(), pessoa.getCep());
    }
}
