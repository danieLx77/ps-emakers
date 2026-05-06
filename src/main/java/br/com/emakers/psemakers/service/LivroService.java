package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.dto.response.LivroResponse;
import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public LivroResponse cadastrarLivro(LivroRequest livroRequest){
        Livro livro = livroRepository.save(new Livro(livroRequest));
        return new LivroResponse(livro);
    }

    public List<LivroResponse> listarTodosLivros(){
        return livroRepository.findAll().stream().map(LivroResponse::new).toList();
    }

    public LivroResponse buscarLivroPorId(Long id){
        return livroRepository.findById(id).map(LivroResponse::new).
                orElseThrow(()-> new RuntimeException("Livro não encontrado."));
    }

    public LivroResponse atualizarLivro(Long id, LivroRequest livroRequest){
        return livroRepository.findById(id)
                .map(livroEncontrado ->{
                    livroEncontrado.atualizarDados(livroRequest);
                    return new LivroResponse(livroRepository.save(livroEncontrado));
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado para atualizar"));
    }

    public void deletarLivro(Long id){
        livroRepository.deleteById(id);
    }

}
