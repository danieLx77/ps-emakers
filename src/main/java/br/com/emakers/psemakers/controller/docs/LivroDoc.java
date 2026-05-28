package br.com.emakers.psemakers.controller.docs;

import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.dto.response.LivroResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Tag(name = "Livros", description = "Endpoints para o gerenciamento do acervo de livros")
public interface LivroDoc {

    @Operation(summary = "Cadastra un novo livro", description = "Exige token JWT. Salva um livro no acervo com status ATIVO.")
    @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos", content = @Content)
    @ApiResponse(responseCode = "403", description = "Acesso negado / Token inválido ou ausente", content = @Content)
    ResponseEntity<LivroResponse> cadastrarLivro(LivroRequest livroRequest);

    @Operation(summary = "Lista todos os livros ativos", description = "Exige token JWT. Retorna os livros disponíveis e emprestados que não sofreram soft delete.")
    @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content)
    ResponseEntity<List<LivroResponse>> listarTodosLivros();

    @Operation(summary = "Busca um livro pelo ID", description = "Exige token JWT. Retorna as informações detalhadas do livro.")
    @ApiResponse(responseCode = "200", description = "Livro encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Livro não encontrado ou inativo", content = @Content)
    ResponseEntity<LivroResponse> buscarLivroPorId(Long id);

    @Operation(summary = "Atualiza as informações de um livro", description = "Exige token JWT. Permite modificar campos como título, autor, etc.")
    @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos", content = @Content)
    @ApiResponse(responseCode = "404", description = "Livro não encontrado", content = @Content)
    ResponseEntity<LivroResponse> atualizarLivro(Long id, LivroRequest livroRequest);

    @Operation(summary = "Inativa um livro (Soft Delete)", description = "Exige token JWT. Altera o status do livro para INATIVO.")
    @ApiResponse(responseCode = "204", description = "Livro inativado com sucesso")
    @ApiResponse(responseCode = "44", description = "Livro não encontrado", content = @Content)
    ResponseEntity<Void> deletarLivro(Long id);
}