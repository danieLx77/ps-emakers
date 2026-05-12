package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.dto.response.PessoaResponse;
import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.entity.Pessoa;
import br.com.emakers.psemakers.data.repository.LivroRepository;
import br.com.emakers.psemakers.data.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LivroRepository livroRepository;

    public PessoaResponse cadastrarPessoa(PessoaRequest pessoaRequest){
        Pessoa pessoa = pessoaRepository.save(new Pessoa(pessoaRequest));

        return new PessoaResponse(pessoa);
    }

    public List<PessoaResponse> listarTodasPessoas(){
        return pessoaRepository.findAll().stream().map(PessoaResponse::new).toList();
    }

    public PessoaResponse buscarPorId(Long id){
        return pessoaRepository.findById(id).map(PessoaResponse::new).
                orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public PessoaResponse atualizarPessoa(Long id, PessoaRequest pessoaRequest){
        return pessoaRepository.findById(id)
                .map(pessoaExistente -> {
                    pessoaExistente.atualizarDados(pessoaRequest);
                    return new PessoaResponse(pessoaRepository.save(pessoaExistente));
                })
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada para atualizar"));
    }

    public void deletarPessoa(Long id){
       pessoaRepository.deleteById(id);
    }

    @Transactional
    public PessoaResponse emprestarLivro(Long idPessoa, Long idLivro){
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if(!pessoa.getLivros().contains(livro)){
            pessoa.getLivros().add(livro);
        }

        return new PessoaResponse(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaResponse devolverLivro(Long idPessoa, Long idLivro) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        pessoa.getLivros().remove(livro);

        return new PessoaResponse(pessoaRepository.save(pessoa));
    }

    }

