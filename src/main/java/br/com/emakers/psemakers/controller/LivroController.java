package br.com.emakers.psemakers.controller;

import br.com.emakers.psemakers.controller.docs.LivroDoc;
import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.dto.response.LivroResponse;
import br.com.emakers.psemakers.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController implements LivroDoc {

    @Autowired
    private LivroService livroService;

    @Override
    @PostMapping
    public ResponseEntity<LivroResponse> cadastrarLivro(@RequestBody @Valid LivroRequest livroRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.cadastrarLivro(livroRequest));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarTodosLivros(){
        return ResponseEntity.ok(livroService.listarTodosLivros());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarLivroPorId(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarLivroPorId(id));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroRequest livroRequest){
        return ResponseEntity.ok(livroService.atualizarLivro(id, livroRequest));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id){
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}
