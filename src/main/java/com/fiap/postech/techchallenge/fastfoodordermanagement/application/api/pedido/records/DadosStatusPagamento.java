package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;


import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;

public record DadosStatusPagamento(String numeroPedido, StatusPagamento statusPagamento) {
    public DadosStatusPagamento(String numeroPedido, StatusPagamento statusPagamento) {
        this.numeroPedido = numeroPedido;
        this.statusPagamento = statusPagamento;
    }
}
