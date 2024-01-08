package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record DadosCadastroProduto(
        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        BigDecimal preco,

        @NotNull
        Integer quantidade,

        @NotNull
        Categoria categoria)  {

        public List<Produto> convertToProduto(DadosCadastroProduto dadosCadastroProduto){
                return null;
        }
}
