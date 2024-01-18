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
public class GerarNumeroDoPedidoTest {

    private GerarNumeroDoPedido gerarNumeroDoPedido;

    @BeforeEach
    public void init() {
        gerarNumeroDoPedido = new GerarNumeroDoPedido();
         MockMvcBuilders.standaloneSetup(gerarNumeroDoPedido).build();
    }

    @DisplayName("Test - Deve gerar pedido")
    @Test
    public void deveGerarQrCode() {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();

        // Quando
        Pedido pedido = gerarNumeroDoPedido.gerar(dadosPedido.convertToPedido());

        // Entao
       Assertions.assertNotNull(pedido);
    }

}