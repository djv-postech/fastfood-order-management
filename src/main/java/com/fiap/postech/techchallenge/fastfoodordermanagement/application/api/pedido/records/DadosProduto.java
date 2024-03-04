package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosProduto(@NotNull Integer id, @NotNull String nome, @NotNull String descricao, @NotNull BigDecimal preco, @NotNull Categoria categoria,
                           @NotNull Integer quantidade) {

    public DadosProduto(Produto dadosProduto) {
        this(dadosProduto.getId(), dadosProduto.getNome(), dadosProduto.getDescricao(),
                dadosProduto.getPreco(), dadosProduto.getCategoria(), dadosProduto.getQuantidade());
    }
}
