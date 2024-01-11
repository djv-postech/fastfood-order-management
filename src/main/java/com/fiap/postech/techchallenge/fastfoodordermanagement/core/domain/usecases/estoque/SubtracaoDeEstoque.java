package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;

import java.util.List;

public class SubtracaoDeEstoque {

    //FIXME: TAlvez seja melhor criar um endpoint para subtrair uma lista de produtos em vez de ser uma chamada por vez

    private final EstoqueGateway estoqueGateway;


    public SubtracaoDeEstoque(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public void subtrair(List<Produto> produtos){
        produtos.stream().forEach( produto ->
        estoqueGateway.subtrairEstoque(produto.getId(),  produto.getQuantidade()));
    }
}
