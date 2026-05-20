package br.com.emakers.psemakers.exception.livro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroInativoException extends RuntimeException {
    public LivroInativoException(String message) {
        super(message);
    }
}
