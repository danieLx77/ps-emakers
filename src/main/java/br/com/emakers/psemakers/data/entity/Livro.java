package br.com.emakers.psemakers.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String autor;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @ManyToMany(mappedBy = "livros")
    private List<Pessoa> pessoas;
}
