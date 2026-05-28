package br.com.emakers.psemakers.controller.docs;

import br.com.emakers.psemakers.data.dto.request.AuthRequest;
import br.com.emakers.psemakers.data.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Autenticação", description = "Endpoint responsável por gerar as credenciais de acesso (Tokens JWT)")
public interface AuthDoc {

    @Operation(summary = "Realiza o login do usuário", description = "Rota pública. Valida as credenciais e retorna um Token JWT válido por 2 horas.")
    @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso. Retorna o Token.")
    @ApiResponse(responseCode = "400", description = "Dados de login mal formatados", content = @Content)
    @ApiResponse(responseCode = "401", description = "E-mail ou senha incorretos / Usuário inativo", content = @Content)
    ResponseEntity<LoginResponse> login(AuthRequest dados);
}