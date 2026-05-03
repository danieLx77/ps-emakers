package br.com.emakers.psemakers.data.dto.response;

import java.time.LocalDate;

public record LivroResponse (
        Long idLivro,
        String nome,
        String autor,
        LocalDate dataLancamento
) {}
