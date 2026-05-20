package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.client.ViaCepClient;
import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.dto.response.EnderecoResponse;
import br.com.emakers.psemakers.data.dto.response.PessoaResponse;
import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.entity.Pessoa;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import br.com.emakers.psemakers.data.repository.LivroRepository;
import br.com.emakers.psemakers.data.repository.PessoaRepository;
import br.com.emakers.psemakers.exception.pessoa.PessoaInativaException;
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

    @Autowired
    private ViaCepClient viaCepClient;

    @Transactional
    public PessoaResponse cadastrarPessoa(PessoaRequest pessoaRequest){
        Pessoa pessoa = new Pessoa(pessoaRequest);

        preencherEndereco(pessoa);

        return new PessoaResponse(pessoaRepository.save(pessoa));
    }

    public List<PessoaResponse> listarTodasPessoas(){
        return pessoaRepository.findByStatus(StatusRegistro.ATIVO).stream().map(PessoaResponse::new).toList();
    }

    public PessoaResponse buscarPorId(Long id){
        return pessoaRepository.findByIdPessoaAndStatus(id, StatusRegistro.ATIVO).map(PessoaResponse::new).
                orElseThrow(() -> new PessoaInativaException("Pessoa não encontrada"));
    }

    @Transactional
    public PessoaResponse atualizarPessoa(Long id, PessoaRequest pessoaRequest) {
        return pessoaRepository.findByIdPessoaAndStatus(id, StatusRegistro.ATIVO)
                .map(pessoaExistente -> {
                    pessoaExistente.atualizarDados(pessoaRequest);
                    preencherEndereco(pessoaExistente);
                    return new PessoaResponse(pessoaRepository.save(pessoaExistente));
                })
                .orElseThrow(() -> new PessoaInativaException("Pessoa não encontrada para atualizar"));
    }

    @Transactional
    public void deletarPessoa(Long id){
        Pessoa pessoa = pessoaRepository.findByIdPessoaAndStatus(id, StatusRegistro.ATIVO)
                .orElseThrow(() -> new PessoaInativaException("Pessoa não encontrada ou inativa"));

        pessoa.setStatus(StatusRegistro.INATIVO);
        pessoaRepository.save(pessoa);
    }

    @Transactional
    public PessoaResponse emprestarLivro(Long idPessoa, Long idLivro){
        Pessoa pessoa = pessoaRepository.findByIdPessoaAndStatus(idPessoa, StatusRegistro.ATIVO)
                .orElseThrow(() -> new PessoaInativaException("Pessoa não encontrada"));

        Livro livro = livroRepository.findByIdLivroAndStatus(idLivro, StatusRegistro.ATIVO)
                .orElseThrow(() -> new PessoaInativaException("Livro não encontrado"));

        if(!pessoa.getLivros().contains(livro)){
            pessoa.getLivros().add(livro);
        }

        return new PessoaResponse(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaResponse devolverLivro(Long idPessoa, Long idLivro) {
        Pessoa pessoa = pessoaRepository.findByIdPessoaAndStatus(idPessoa, StatusRegistro.ATIVO)
                .orElseThrow(() -> new PessoaInativaException("Pessoa não encontrada"));

        Livro livro = livroRepository.findByIdLivroAndStatus(idLivro, StatusRegistro.ATIVO)
                .orElseThrow(() -> new PessoaInativaException("Livro não encontrado"));

        pessoa.getLivros().remove(livro);

        return new PessoaResponse(pessoaRepository.save(pessoa));
    }

    private void preencherEndereco(Pessoa pessoa) {
        if (pessoa.getCep() != null && !pessoa.getCep().isBlank()) {
            try {
                EnderecoResponse endereco = viaCepClient.buscarEnderecoPorCep(pessoa.getCep());
                if (endereco != null && endereco.logradouro() != null) {
                    pessoa.setLogradouro(endereco.logradouro());
                    pessoa.setBairro(endereco.bairro());
                    pessoa.setCidade(endereco.localidade());
                    pessoa.setUf(endereco.uf());
                }
            } catch (Exception e) {
                System.err.println("Erro ao buscar o CEP " + pessoa.getCep() + ": " + e.getMessage());
            }
        }
    }

    }

