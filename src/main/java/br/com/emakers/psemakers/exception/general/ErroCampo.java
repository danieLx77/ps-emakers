package br.com.emakers.psemakers.exception.general;

public record ErroCampo(
        String campo,
        String mensagem
) {}
