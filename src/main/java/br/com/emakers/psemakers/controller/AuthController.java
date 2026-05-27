package br.com.emakers.psemakers.controller;

import br.com.emakers.psemakers.data.dto.request.AuthRequest;
import br.com.emakers.psemakers.data.dto.response.LoginResponse;
import br.com.emakers.psemakers.data.entity.Pessoa;
import br.com.emakers.psemakers.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthRequest dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Pessoa) Objects.requireNonNull(auth.getPrincipal()));

        return ResponseEntity.ok(new LoginResponse(token));
    }
}