package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AtualizacaoDePedidoTest {

    private AtualizacaoDadosDePagamentoPedido atualizacaoDePedido;

    @BeforeEach
    public void init() {
        atualizacaoDePedido = new AtualizacaoDadosDePagamentoPedido();
        MockMvcBuilders.standaloneSetup(atualizacaoDePedido).build();
    }

    @DisplayName("Test - Deve atualizar pedido")
    @Test
    public void deveAtualizarPedido() {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        String qrCode = "qrCode";

        // Quando
        Pedido pedido = atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido());

        // Entao
        Assertions.assertNotNull(pedido);
        Assertions.assertNotNull(pedido.getPagamento());
    }

}