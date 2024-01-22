package com.fiap.postech.techchallenge.fastfoodordermanagement;



import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Categoria;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;

import java.math.BigDecimal;

public class ProdutoHelper {


    public static DadosProduto gerarDadosProduto() {
        return new DadosProduto(1, "Hamburguer", "Descricao Big Mac", new BigDecimal(10), Categoria.LANCHE, 1);
    }

    public static Produto gerarProduto() {
        return new Produto(1, "Hamburguer", "BigMac", new BigDecimal(10), 1,  Categoria.LANCHE);
    }

    public static DadosSubtracaoEstoqueProduto gerarDadosSubtracaoEstoqueProduto(){
        return new DadosSubtracaoEstoqueProduto(1, 1);
    }


}
