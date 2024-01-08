package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;


import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;

public record StatusPagamentoPedido(String numeroPedido, StatusPagamento statusPagamento) {


}
