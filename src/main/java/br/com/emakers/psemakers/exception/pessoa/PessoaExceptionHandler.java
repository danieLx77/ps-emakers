package br.com.emakers.psemakers.exception.pessoa;

import br.com.emakers.psemakers.exception.general.RestErrorMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(1)
public class PessoaExceptionHandler {

    @ExceptionHandler(PessoaInativaException.class)
    public ResponseEntity<RestErrorMessage> tratarPessoaInativa(PessoaInativaException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestErrorMessage erro = new RestErrorMessage(status, ex.getMessage());
        return ResponseEntity.status(status).body(erro);
    }
}
