package br.com.emakers.psemakers.service;

import br.com.emakers.psemakers.data.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return pessoaRepository.findByEmailAndStatus(username, br.com.emakers.psemakers.data.enuns.StatusRegistro.ATIVO)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado ou inativo"));
    }
}
