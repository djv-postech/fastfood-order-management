package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento.exception;

public class QrCodePagamentoException extends RuntimeException {
    public QrCodePagamentoException(String message) {
        super(message);
    }
}
