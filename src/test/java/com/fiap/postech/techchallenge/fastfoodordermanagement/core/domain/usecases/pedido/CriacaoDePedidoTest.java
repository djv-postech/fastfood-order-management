package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp.ProducaoAMQPConfiguration.PEDIDO_PRODUCAO_EX;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CriacaoDePedidoTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    private CriacaoDePedido criacaoDePedido;

    @BeforeEach
    public void init() {
        criacaoDePedido = new CriacaoDePedido();
        ReflectionTestUtils.setField(criacaoDePedido, "rabbitTemplate", rabbitTemplate);
        MockMvcBuilders.standaloneSetup(criacaoDePedido).build();
    }

    @DisplayName("Test - Deve criar pedido")
    @Test
    public void deveCriarPedido() {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();

        // Quando
        criacaoDePedido.criar(dadosPedido);

        // Entao
        verify(rabbitTemplate, times(1)).convertAndSend(PEDIDO_PRODUCAO_EX, "", dadosPedido);
    }

}

