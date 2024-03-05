package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoqueMessageService;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.SolicitacaoDePagamentoMessageService;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.GerarNumeroDoPedido;
import org.assertj.core.api.Assertions;
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
    private SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService;

    @Mock
    private AtualizacaoDePedido atualizacaoDePedido;

    @Mock
    private GerarQrCode gerarQrCode;

    @Mock
    private GerarNumeroDoPedido gerarNumeroDoPedido;

    @Mock
    private SolicitacaoDePagamentoMessageService solicitacaoDePagamentoMessageService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        PedidoController pedidoController = new PedidoController(registroDeCliente, subtracaoDeEstoqueMessageService, solicitacaoDePagamentoMessageService, gerarNumeroDoPedido);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @DisplayName("Test - Deve criar pedido")
    @Test
    public void deveRegistrarClienteSubtrairEstoqueGerarQrCodeEEnviarPedidoParaProducao() throws Exception {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.gerarDadosCadastroPedido();
        when(gerarNumeroDoPedido.gerar(any())).thenReturn(dadosPedido.convertToPedido());

        // Quando
        mockMvc.perform(post("/pedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(dadosCadastroPedido)))
                .andExpect(status().isOk());

        // Entao
        verify(solicitacaoDePagamentoMessageService, times(1))
                .solicitacaoDePagamento(any(Pedido.class));
        verify(subtracaoDeEstoqueMessageService, times(1))
                .subtrairEstoque(any());

    }

    @DisplayName("Test - Deve retornar bad request quando CPF do cliente é inválido")
    @Test
    public void deveRetornarBadRequestQuandoCPFClienteEInvalido() throws Exception {
        // Dado
        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.gerarDadosCadastroPedidoComCPFClienteInvalido();

        // Quando
        //Então
        Assertions
                .assertThatThrownBy(
                        () -> mockMvc.perform(post("/pedido")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(dadosCadastroPedido))).andExpect(status().isInternalServerError()))
                .hasCauseInstanceOf(RuntimeException.class).hasMessageContaining("CPF inválido");
    }

    @DisplayName("Test - Deve retornar bad request quando email do cliente é inválido")
    @Test
    public void deveRetornarBadRequestQuandoEmailDoClienteEInvalido() throws Exception {
        // Dado
        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.gerarDadosCadastroPedidoComEmailClienteInvalido();

        // Quando
        //Então
        Assertions
                .assertThatThrownBy(
                        () -> mockMvc.perform(post("/pedido")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(dadosCadastroPedido))).andExpect(status().isInternalServerError()))
                .hasCauseInstanceOf(RuntimeException.class).hasMessageContaining("Email inválido");
    }

    @DisplayName("Test - Deve retornar bad request quando email do cliente é nulo")
    @Test
    public void deveRetornarBadRequestQuandoEmailDoClienteENulo() throws Exception {
        // Dado
        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.gerarDadosCadastroPedidoComEmailClienteNulo();

        // Quando
        //Então
        Assertions
                .assertThatThrownBy(
                        () -> mockMvc.perform(post("/pedido")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(dadosCadastroPedido))).andExpect(status().isInternalServerError()))
                .hasCauseInstanceOf(RuntimeException.class).hasMessageContaining("Email inválido");
    }



    public static String convertToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }
}