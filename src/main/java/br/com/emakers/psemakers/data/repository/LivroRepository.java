package br.com.emakers.psemakers.data.repository;

import br.com.emakers.psemakers.data.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
