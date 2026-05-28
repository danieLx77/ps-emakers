package br.com.emakers.psemakers.controller.docs;

import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.dto.response.PessoaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import java.util.List;

@Tag(name = "Pessoas", description = "Endpoints para gerenciamento de usuários e fluxos de empréstimos")
public interface PessoaDoc {

    @Operation(summary = "Cadastra uma nova pessoa", description = "Rota pública. Cria um usuário com senha criptografada e status ATIVO.")
    @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos (erro de validação)", content = @Content)
    ResponseEntity<PessoaResponse> cadastrarPessoa(PessoaRequest request);

    @Operation(summary = "Lista todas as pessoas ativas", description = "Exige token JWT. Retorna a lista de usuários que não sofreram soft delete.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @ApiResponse(responseCode = "403", description = "Acesso negado / Token inválido ou ausente", content = @Content)
    ResponseEntity<List<PessoaResponse>> listarTodasPessoas();

    @Operation(summary = "Busca uma pessoa pelo ID", description = "Exige token JWT. Retorna os dados detalhados da pessoa informada.")
    @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada ou inativa", content = @Content)
    ResponseEntity<PessoaResponse> buscarPorId(Long id);

    @Operation(summary = "Atualiza os dados de uma pessoa", description = "Exige token JWT. Permite modificar os campos cadastrais.")
    @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados enviados são inválidos", content = @Content)
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    ResponseEntity<PessoaResponse> atualizarPessoa(Long id, PessoaRequest request);

    @Operation(summary = "Inativa uma pessoa (Soft Delete)", description = "Exige token JWT. Altera o status da pessoa para INATIVO.")
    @ApiResponse(responseCode = "204", description = "Pessoa inativada com sucesso")
    @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content)
    ResponseEntity<Void> deletarPessoa(Long id);

    @Operation(summary = "Realiza o empréstimo de um livro", description = "Exige token JWT. Vincula um livro disponível a uma pessoa ativa.")
    @ApiResponse(responseCode = "200", description = "Empréstimo realizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Regra de negócio violada (Ex: livro indisponível)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Pessoa ou Livro não encontrados", content = @Content)
    ResponseEntity<PessoaResponse> emprestar(Long idPessoa, Long idLivro);

    @Operation(summary = "Realiza a devolução de um livro", description = "Exige token JWT. Desvincula o livro do histórico atual da pessoa.")
    @ApiResponse(responseCode = "200", description = "Devolução realizada com sucesso")
    @ApiResponse(responseCode = "400", description = "Regra de negócio violada (Ex: livro não estava emprestado para esta pessoa)", content = @Content)
    @ApiResponse(responseCode = "404", description = "Pessoa ou Livro não encontrados", content = @Content)
    ResponseEntity<PessoaResponse> devolver(Long idPessoa, Long idLivro);
}