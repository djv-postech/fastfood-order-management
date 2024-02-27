package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoqueMessageService;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueBeanConfiguration {

    private final EstoqueGateway estoqueGateway;
    private final RabbitTemplate rabbitTemplate;

    public EstoqueBeanConfiguration(EstoqueGateway estoqueGateway, RabbitTemplate rabbitTemplate) {
        this.estoqueGateway = estoqueGateway;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public SubtracaoDeEstoque subtracaoDeEstoque() {
        return new SubtracaoDeEstoque(estoqueGateway);
    }

    @Bean
    public SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService() {
        return new SubtracaoDeEstoqueMessageService(rabbitTemplate);
    }

}
