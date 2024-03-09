package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducaoAMQPConfiguration {
    public static final String PEDIDO_PRODUCAO_EX = "ex.pedido_producao";
    public static final String PEDIDO_PRODUCAO_COZINHA_QUEUE = "queue.pedido_producao_cozinha";
    public static final String PEDIDO_PRODUCAO_DLX = "dlx.pedido_producao";
    public static final String PEDIDO_PRODUCAO_COZINHA_DLQ = "dlq.pedido_producao_cozinha";
    public static final String PRODUTO_ESTOQUE_EX = "ex.produto_estoque";
    public static final String PRODUTO_ESTOQUE_SUBTRACAO_QUEUE = "queue.produto_estoque_subtracao";
    public static final String PRODUTO_ESTOQUE_DLX = "dlx.produto_estoque";
    public static final String PRODUTO_ESTOQUE_SUBTRACAO_DLQ = "dlq.produto_estoque_subtracao";

    public static final String SOLICITACAO_PAGAMENTO_EX =  "ex.solicitacao_pagamento";


    @Bean
    public RabbitAdmin criarAdminConfig(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> startAdmin(RabbitAdmin rabbitAdmin){
        return  event -> rabbitAdmin.initialize();
    }

    @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            return new Jackson2JsonMessageConverter();
        }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }


    @Bean
    public Queue pedidoProducaoCozinhaQueue(){
        return QueueBuilder.nonDurable(PEDIDO_PRODUCAO_COZINHA_QUEUE)
                .deadLetterExchange(PEDIDO_PRODUCAO_DLX)
                .build();
    }

    @Bean
    public Queue produtoEstoqueSubtracaoQueue(){
        return QueueBuilder.nonDurable(PRODUTO_ESTOQUE_SUBTRACAO_QUEUE)
                .deadLetterExchange(PRODUTO_ESTOQUE_DLX)
                .build();
    }

    @Bean
    public FanoutExchange pedidoProducaoExchange(){
        return new FanoutExchange(PEDIDO_PRODUCAO_EX);
    }

    @Bean
    public FanoutExchange produtoEstoqueExchange(){
        return new FanoutExchange(PRODUTO_ESTOQUE_EX);
    }

    @Bean
    public Binding pedidoProducaoBinding(){
        return BindingBuilder.bind(pedidoProducaoCozinhaQueue()).to(pedidoProducaoExchange());

    }

    @Bean
    public Binding produtoEstoqueBinding(){
        return BindingBuilder.bind(produtoEstoqueSubtracaoQueue()).to(produtoEstoqueExchange());

    }

    @Bean
    public FanoutExchange pedidoProducaoDLX(){
        return new FanoutExchange(PEDIDO_PRODUCAO_DLX);
    }

    @Bean
    public FanoutExchange produtoEstoqueDLX(){
        return new FanoutExchange(PRODUTO_ESTOQUE_DLX);
    }

    @Bean
    public Queue pedidoProducaoCozinhaDLQ(){
        return QueueBuilder.nonDurable(PEDIDO_PRODUCAO_COZINHA_DLQ)
                .build();
    }

    @Bean
    public Queue produtoEstoqueSubtracaoDLQ(){
        return QueueBuilder.nonDurable(PRODUTO_ESTOQUE_SUBTRACAO_DLQ)
                .build();
    }

    @Bean
    public Binding pedidoProducaoDLXDLQBinding(){
        return BindingBuilder.bind(pedidoProducaoCozinhaDLQ()).to(pedidoProducaoDLX());

    }

    @Bean
    public Binding produtoEstoqueDLXDLQBinding(){
        return BindingBuilder.bind(produtoEstoqueSubtracaoDLQ()).to(produtoEstoqueDLX());

    }
}
