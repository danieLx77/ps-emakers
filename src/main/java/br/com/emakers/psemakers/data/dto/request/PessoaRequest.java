package br.com.emakers.psemakers.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaRequest (
        @NotBlank(message = "É obrigatório informar o nome.")
        String nome,

        @NotBlank(message = "É obrigatório informar o CPF.")
        @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
        String cpf,

        @NotBlank(message = "É obrigatório informar o e-mail.")
        @Email(message = "Email inválido")
        String email,

        @NotBlank(message = "É obrigatório informar a senha.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String senha,

        String cep
) {}
