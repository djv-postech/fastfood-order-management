package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;

import java.math.BigDecimal;

public record DadosProduto(Integer id, String nome, String descricao, BigDecimal preco, Categoria categoria,
                           Integer quantidade) {

    public DadosProduto(Produto dadosProduto) {
        this(dadosProduto.getId(), dadosProduto.getNome(), dadosProduto.getDescricao(),
                dadosProduto.getPreco(), dadosProduto.getCategoria(), dadosProduto.getQuantidade());
    }
}
