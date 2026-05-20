package br.com.emakers.psemakers.data.repository;

import br.com.emakers.psemakers.data.entity.Pessoa;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByIdPessoaAndStatus(Long id, StatusRegistro status);
    List<Pessoa> findByStatus(StatusRegistro status);
}
