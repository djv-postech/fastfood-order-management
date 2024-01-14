package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;

import java.util.List;
import java.util.stream.Collectors;

public class SubtracaoDeEstoque {

    private final EstoqueGateway estoqueGateway;

    public SubtracaoDeEstoque(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public void subtrair(List<Produto> produtos) {
        estoqueGateway.subtrairEstoque(produtos.stream().map(produto -> new DadosSubtracaoEstoqueProduto(produto.getId(), produto.getQuantidade())).collect(Collectors.toList()));
    }
}
