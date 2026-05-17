package br.com.emakers.psemakers.data.entity;

import br.com.emakers.psemakers.data.dto.request.LivroRequest;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRegistro status = StatusRegistro.ATIVO;

    @ManyToMany(mappedBy = "livros")
    private List<Pessoa> pessoas = new ArrayList<>();

    @Builder
    public Livro(LivroRequest dto){
        atualizarDados(dto);
    }

    public void atualizarDados(LivroRequest dto){
        this.autor = dto.autor();
        this.dataLancamento = dto.dataLancamento();
        this.nome = dto.nome();
    }


}
