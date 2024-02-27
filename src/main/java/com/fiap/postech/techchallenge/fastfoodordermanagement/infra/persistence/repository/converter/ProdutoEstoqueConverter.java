package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoEstoqueConverter {

    public static List<DadosSubtracaoEstoqueProduto> convertFrom(List<Produto> produtos){
        return produtos.stream()
                .map(produto -> new DadosSubtracaoEstoqueProduto(
                        produto.getId(), produto.getQuantidade())).collect(Collectors.toList());
    }
}
