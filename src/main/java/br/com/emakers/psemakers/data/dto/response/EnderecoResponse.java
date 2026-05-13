package br.com.emakers.psemakers.data.dto.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade, // O ViaCEP usa 'localidade' para o nome da cidade
        String uf
) {}
