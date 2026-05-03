package br.com.emakers.psemakers.data.dto.response;

public record PessoaResponse (
        Long idPessoa,
        String nome,
        String cpf,
        String email,
        String cep
) {}
