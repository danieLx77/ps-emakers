package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.dto.response.LivroResponse;
import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import br.com.emakers.psemakers.data.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public LivroResponse cadastrarLivro(LivroRequest livroRequest){
        Livro livro = livroRepository.save(new Livro(livroRequest));
        return new LivroResponse(livro);
    }

    public List<LivroResponse> listarTodosLivros(){
        return livroRepository.findByStatus(StatusRegistro.ATIVO).stream().map(LivroResponse::new).toList();
    }

    public LivroResponse buscarLivroPorId(Long id){
        return livroRepository.findByIdAndStatus(id, StatusRegistro.ATIVO).map(LivroResponse::new).
                orElseThrow(()-> new RuntimeException("Livro não encontrado."));
    }

    @Transactional
    public LivroResponse atualizarLivro(Long id, LivroRequest livroRequest){
        return livroRepository.findByIdAndStatus(id, StatusRegistro.ATIVO)
                .map(livroEncontrado ->{
                    livroEncontrado.atualizarDados(livroRequest);
                    return new LivroResponse(livroRepository.save(livroEncontrado));
                })
                .orElseThrow(() -> new RuntimeException("Livro não encontrado para atualizar"));
    }

    @Transactional
    public void deletarLivro(Long id){
        Livro livro = livroRepository.findByIdAndStatus(id, StatusRegistro.ATIVO)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setStatus(StatusRegistro.INATIVO);
        livroRepository.save(livro);
    }

}
