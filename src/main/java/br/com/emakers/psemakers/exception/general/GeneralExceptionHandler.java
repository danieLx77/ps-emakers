package br.com.emakers.psemakers.exception.general;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@Order(2)
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> tratarExcecaoGeral(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        RestErrorMessage erro = new RestErrorMessage(status, "Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidacaoErroResposta> tratarErroValidacao(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        List<ErroCampo> camposComErro = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErroCampo(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ValidacaoErroResposta erro = new ValidacaoErroResposta(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                "Houve um erro na validação dos campos enviados",
                camposComErro
        );

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RestErrorMessage> tratarDuplicidadeBanco(DataIntegrityViolationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String mensagem = "Não foi possível realizar a operação: já existe um registro com este CPF e/ou E-mail.";

        if (ex.getMessage() != null && ex.getMessage().contains("pessoa_email_key")) {
            mensagem = "O e-mail informado já está cadastrado no sistema.";
        } else if (ex.getMessage() != null && ex.getMessage().contains("pessoa_cpf_key")) {
            mensagem = "O CPF informado já está cadastrado no sistema.";
        }

        RestErrorMessage erro = new RestErrorMessage(status, mensagem);
        return ResponseEntity.status(status).body(erro);
    }
}