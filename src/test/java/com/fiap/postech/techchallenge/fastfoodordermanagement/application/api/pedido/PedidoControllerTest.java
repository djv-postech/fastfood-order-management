package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.GerarNumeroDoPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {
    @Mock
    private RegistroDeCliente registroDeCliente;

    @Mock
    private CriacaoDePedido criacaoDePedido;

    @Mock
    private SubtracaoDeEstoque subtracaoDeEstoque;

    @Mock
    private AtualizacaoDePedido atualizacaoDePedido;

    @Mock
    private GerarQrCode gerarQrCode;

    @Mock
    private GerarNumeroDoPedido gerarNumeroDoPedido;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        PedidoController pedidoController = new PedidoController(registroDeCliente, criacaoDePedido, subtracaoDeEstoque, atualizacaoDePedido, gerarQrCode, gerarNumeroDoPedido);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @DisplayName("Test - Deve criar pedido")
    @Test
    public void deveRegistrarClienteSubtrairEstoqueGerarQrCodeEEnviarPedidoParaProducao() throws Exception {
        // Dado
        String qrCode = "qrCode";
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        when(gerarNumeroDoPedido.gerar(any())).thenReturn(dadosPedido.convertToPedido());
        when(gerarQrCode.gerar(any())).thenReturn(qrCode);
        when(atualizacaoDePedido.atualizarPedido(any(), any())).thenReturn(dadosPedido.convertToPedido());

        // Quando
        mockMvc.perform(post("/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(dadosPedido)))
                .andExpect(status().isOk());

        // Entao
        verify(criacaoDePedido, times(1))
                .criar(any(DadosPedido.class));

    }

    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}