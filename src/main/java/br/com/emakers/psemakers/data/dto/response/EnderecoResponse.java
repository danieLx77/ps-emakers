package br.com.emakers.psemakers.data.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String uf
) {}
