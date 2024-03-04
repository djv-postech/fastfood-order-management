package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp.ProducaoAMQPConfiguration.SOLICITACAO_PAGAMENTO_QUEUE;

@Slf4j
public class SolicitacaoDePagamentoMessageService {

    private final RabbitTemplate rabbitTemplate;

    public SolicitacaoDePagamentoMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void solicitacaoDePagamento(Pedido pedido) {
        DadosPedido dadosPedido = new DadosPedido(pedido);
        rabbitTemplate.convertAndSend(SOLICITACAO_PAGAMENTO_QUEUE, "", dadosPedido);
        log.info("Solicitação de pagamento enviada. Payload: {}", dadosPedido);
    }
}

