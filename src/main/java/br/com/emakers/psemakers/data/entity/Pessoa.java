package br.com.emakers.psemakers.data.entity;

import br.com.emakers.psemakers.data.dto.request.PessoaRequest;
import br.com.emakers.psemakers.data.enuns.StatusRegistro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pessoa implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(length = 9)
    private String cep;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(length = 150)
    private String logradouro;

    @Column(length = 100)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusRegistro status = StatusRegistro.ATIVO;

    @ManyToMany
    @JoinTable(
            name = "emprestimo",
            joinColumns = @JoinColumn(name = "id_pessoa"),
            inverseJoinColumns = @JoinColumn (name = "id_livro")
    )
    private List<Livro> livros = new ArrayList<>();

    @Builder
    public Pessoa(PessoaRequest dto){
       atualizarDados(dto);
    }

    public void atualizarDados(PessoaRequest dto){
        this.nome = dto.nome();
        this.cep = dto.cep();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.senha = dto.senha();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == StatusRegistro.ATIVO;
    }
}
