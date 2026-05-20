package br.com.emakers.psemakers.exception.pessoa;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaInativaException extends RuntimeException {
    public PessoaInativaException(String message) {
        super(message);
    }
}
