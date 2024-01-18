package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoFeignGatewayTest {


    @Mock
    private PagamentoFeignClient feignClient;

    private PagamentoFeignGateway pagamentoFeignGateway;

    @BeforeEach
    public void init() {
        pagamentoFeignGateway = new PagamentoFeignGateway(feignClient);
        MockMvcBuilders.standaloneSetup(pagamentoFeignGateway).build();
    }

    @DisplayName("Test - Gerar QrCode")
    @Test
    public void deveGerarQRCode() {
        // Dado
        String qrCode = "qrCode";
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        when(feignClient.gerarPagamento(dadosPedido)).thenReturn(qrCode);

        // Quando
        pagamentoFeignGateway.gerarQrCode(dadosPedido);

        // Entao
        verify(feignClient, times(1)).gerarPagamento(dadosPedido);
    }
}