package br.com.emakers.psemakers.data.repository;

import br.com.emakers.psemakers.data.entity.Livro;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIdAndStatus(Long id, StatusRegistro status);
    List<Livro> findByStatus(StatusRegistro status);
}
