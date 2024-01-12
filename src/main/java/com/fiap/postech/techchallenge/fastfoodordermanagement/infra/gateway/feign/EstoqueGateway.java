package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;

import java.util.List;

public interface EstoqueGateway {

   void subtrairEstoque(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto);
}
