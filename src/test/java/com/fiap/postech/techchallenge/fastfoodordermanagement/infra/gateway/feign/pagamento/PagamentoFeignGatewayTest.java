package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception.SubtracaoDeEstoqueException;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento.exception.QrCodePagamentoException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @DisplayName("Test - Deve retornar QrCodePagamentoException quando sistema de pagamento retornar erro")
    @Test
    public void deveGerarQrCodePagamentoExceptionQuandoPagamentoRetornarErro() {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        Map<String, Collection<String>> headers = new HashMap<>();

        doThrow(FeignException.errorStatus("any", Response.builder()
                .status(500)
                .request(Request.create(Request.HttpMethod.POST, "url", headers, "".getBytes(), StandardCharsets.UTF_8))
                .build()))
                .when(feignClient)
                .gerarPagamento(dadosPedido);


        // Quando
        // Entao
        Assertions.assertThrows(
                QrCodePagamentoException.class, () -> pagamentoFeignGateway.gerarQrCode(dadosPedido));
        verify(feignClient, times(1)).gerarPagamento(dadosPedido);
    }
}