package com.fiap.postech.techchallenge.fastfoodordermanagement.application.amqp;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDadosDePagamentoPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QrCodeListenerTest {

    @Mock
    private AtualizacaoDadosDePagamentoPedido atualizacaoDadosDePagamentoPedido;

    @Mock
    private CriacaoDePedido criacaoDePedido;

    private QrCodeListener qrCodeListener;

    @BeforeEach
    public void init() {
        qrCodeListener = new QrCodeListener(atualizacaoDadosDePagamentoPedido, criacaoDePedido);
        MockMvcBuilders.standaloneSetup(qrCodeListener).build();
    }

    @DisplayName("Test - Deve atualizar pagamento e enviar criação de pedido")
    @Test
    public void aoReceberQrCodeDeveAtualizarDadosDePagamentoEEnviarCriacaoDePedido() throws Exception {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();

        // Quando
        qrCodeListener.cadastrarPedido(dadosPedido);

        // Entao
        verify(criacaoDePedido, times(1)).criar(any(DadosPedido.class));
    }


}