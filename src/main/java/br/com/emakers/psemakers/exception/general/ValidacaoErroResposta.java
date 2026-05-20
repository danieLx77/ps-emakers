package br.com.emakers.psemakers.exception.general;

import java.time.LocalDateTime;
import java.util.List;

public record ValidacaoErroResposta(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        List<ErroCampo> erros
) {}