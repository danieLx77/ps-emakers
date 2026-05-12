package br.com.emakers.psemakers.data.dto.response;

import br.com.emakers.psemakers.data.entity.Pessoa;

import java.util.List;

public record PessoaResponse (
        Long idPessoa,
        String nome,
        String cpf,
        String email,
        String cep,
        List<LivroResponse> livros
) {
    public PessoaResponse(Pessoa pessoa){
        this(pessoa.getIdPessoa(), pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail(), pessoa.getCep(),
                pessoa.getLivros() != null ?
                        pessoa.getLivros().stream().map(LivroResponse::new).toList(): java.util.Collections.emptyList());
    }
}
