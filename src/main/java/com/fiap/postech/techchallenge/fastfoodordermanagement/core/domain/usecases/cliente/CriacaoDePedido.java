package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp.ProducaoAMQPConfiguration.PEDIDO_PRODUCAO_EX;

public class CriacaoDePedido {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void criar(DadosCadastroPedido dadosCadastroPedido){

        rabbitTemplate.convertAndSend(PEDIDO_PRODUCAO_EX, "", dadosCadastroPedido);

    }
}
