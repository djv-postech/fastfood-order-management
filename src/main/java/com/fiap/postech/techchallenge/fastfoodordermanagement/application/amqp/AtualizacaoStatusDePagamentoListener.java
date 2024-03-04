package com.fiap.postech.techchallenge.fastfoodordermanagement.application.amqp;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosStatusPagamento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp.ProducaoAMQPConfiguration.STATUS_PAGAMENTO;

@Slf4j
public class AtualizacaoStatusDePagamentoListener {
    private final RabbitTemplate rabbitTemplate;

    public AtualizacaoStatusDePagamentoListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = STATUS_PAGAMENTO)
    public void atualizarStatusDePagamento(DadosStatusPagamento dadosStatusPagamento) {

        //TODO: Chamar sistema de notificações

        //TODO: Enviar atualização para sistema produçao


        log.info("Atualização de pagamento recebida. Payload: {}", dadosStatusPagamento);
    }
}

