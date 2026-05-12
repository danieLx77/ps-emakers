package br.com.emakers.psemakers.controller;

import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.dto.response.PessoaResponse;
import br.com.emakers.psemakers.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> cadastrarPessoa(@RequestBody @Valid PessoaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.cadastrarPessoa(request));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarTodasPessoas() {
        return ResponseEntity.ok(pessoaService.listarTodasPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Long id, @RequestBody @Valid PessoaRequest request) {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{idPessoa}/emprestar/{idLivro}")
    public ResponseEntity<PessoaResponse> emprestar(@PathVariable Long idPessoa, @PathVariable Long idLivro) {
        return ResponseEntity.ok(pessoaService.emprestarLivro(idPessoa, idLivro));
    }

    @PatchMapping("/{idPessoa}/devolver/{idLivro}")
    public ResponseEntity<PessoaResponse> devolver(@PathVariable Long idPessoa, @PathVariable Long idLivro) {
        return ResponseEntity.ok(pessoaService.devolverLivro(idPessoa, idLivro));
    }
}