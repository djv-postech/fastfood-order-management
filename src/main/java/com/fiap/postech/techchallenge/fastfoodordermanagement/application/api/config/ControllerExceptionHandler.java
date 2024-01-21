package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.config;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception.SubtracaoDeEstoqueException;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento.exception.QrCodePagamentoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {SubtracaoDeEstoqueException.class})
    protected ResponseEntity<ApiError> handlerSubtracaoDeEstoqueException(SubtracaoDeEstoqueException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<ApiError> handlerIllegalArgumentException(IllegalArgumentException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {QrCodePagamentoException.class})
    protected ResponseEntity<ApiError> handlerQrCodePagamentoException(QrCodePagamentoException ex) {
        ApiError error = new ApiError(ex.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
