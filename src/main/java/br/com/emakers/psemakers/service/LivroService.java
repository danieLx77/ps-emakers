package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.dto.response.LivroResponse;
import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import br.com.emakers.psemakers.data.repository.LivroRepository;
import br.com.emakers.psemakers.exception.livro.LivroInativoException;
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
        return livroRepository.findByIdLivroAndStatus(id, StatusRegistro.ATIVO).map(LivroResponse::new).
                orElseThrow(()-> new LivroInativoException("Livro não encontrado."));
    }

    @Transactional
    public LivroResponse atualizarLivro(Long id, LivroRequest livroRequest){
        return livroRepository.findByIdLivroAndStatus(id, StatusRegistro.ATIVO)
                .map(livroEncontrado ->{
                    livroEncontrado.atualizarDados(livroRequest);
                    return new LivroResponse(livroRepository.save(livroEncontrado));
                })
                .orElseThrow(() -> new LivroInativoException("Livro não encontrado para atualizar"));
    }

    @Transactional
    public void deletarLivro(Long id){
        Livro livro = livroRepository.findByIdLivroAndStatus(id, StatusRegistro.ATIVO)
                .orElseThrow(() -> new LivroInativoException("Livro não encontrado"));

        livro.setStatus(StatusRegistro.INATIVO);
        livroRepository.save(livro);
    }

}
