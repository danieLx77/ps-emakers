package br.com.emakers.psemakers.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record LivroRequest (
        @NotBlank(message = "É obrigatório informar o título.")
        String nome,

        @NotBlank(message = "É obrigatório informar o nome do autor.")
        String autor,

        LocalDate dataLancamento
) {}

