package com.fiap.postech.techchallenge.fastfoodordermanagement;


import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoHelper {

    public static DadosPedido gerarDadosPedido() {
        return new DadosPedido(new Pedido(null, new Cliente("Cliente", new CPF("123.456.789-01"), new Email("email@email.com")), List.of(ProdutoHelper.gerarProduto()), BigDecimal.valueOf(10), PagamentoHelper.gerarPagamento(), StatusPedido.RECEBIDO, null, LocalDateTime.now()));
    }

    public static DadosCadastroPedido gerarDadosCadastroPedido() {
        return new DadosCadastroPedido(List.of(ProdutoHelper.gerarDadosProduto()), new DadosCliente("Cliente", "123.456.789-01", "email@email.com"), StatusPedido.RECEBIDO, LocalDateTime.now(), BigDecimal.valueOf(10));
    }

}
