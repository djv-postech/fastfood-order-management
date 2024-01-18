package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GerarQrCodeTest {

    @Mock
    private PagamentoGateway pagamentoGateway;
    private MockMvc mockMvc;

    private GerarQrCode gerarQrCode;

    @BeforeEach
    public void init() {
        gerarQrCode = new GerarQrCode(pagamentoGateway);
        this.mockMvc = MockMvcBuilders.standaloneSetup(gerarQrCode).build();
    }

    @DisplayName("Test - Deve gerar pedido")
    @Test
    public void deveGerarQrCode() {
        // Dado
        String qrCode = "qrCode";
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        when(pagamentoGateway.gerarQrCode(dadosPedido)).thenReturn(qrCode);

        // Quando
        String qrCodeGerado = gerarQrCode.gerar(dadosPedido);

        // Entao
        verify(pagamentoGateway, times(1)).gerarQrCode(any());
        Assertions.assertNotNull(qrCodeGerado);

    }

}