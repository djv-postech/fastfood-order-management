package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception;

public class SubtracaoDeEstoqueException extends RuntimeException {
    public SubtracaoDeEstoqueException(String message) {
        super(message);
    }
}