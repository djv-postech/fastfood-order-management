package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitacaoDePagamentoMessageServiceTest {

    @Mock
    private RabbitTemplate rabbitTemplate;
    private MockMvc mockMvc;

    private SolicitacaoDePagamentoMessageService solicitacaoDePagamentoMessageService;

    @BeforeEach
    public void init() {
        solicitacaoDePagamentoMessageService = new SolicitacaoDePagamentoMessageService(rabbitTemplate);
        this.mockMvc = MockMvcBuilders.standaloneSetup(solicitacaoDePagamentoMessageService).build();
    }

    @DisplayName("Test - Deve gerar solicitação de pagamento")
    @Test
    public void deveGerarSolicitacaoDePagamento() {
        // Dado
        Pedido pedido = PedidoHelper.gerarDadosPedido().convertToPedido();

        // Quando
        solicitacaoDePagamentoMessageService.solicitarPagamento(pedido);

        // Entao
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), any(DadosPedido.class));
    }
}