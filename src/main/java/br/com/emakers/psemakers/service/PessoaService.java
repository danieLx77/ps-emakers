package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.dto.response.PessoaResponse;
import br.com.emakers.psemakers.data.entity.Pessoa;
import br.com.emakers.psemakers.data.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

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

    }

