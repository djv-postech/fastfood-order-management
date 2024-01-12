package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.EstoqueClientProperties;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.EstoqueFeignClient;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.EstoqueFeignGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueBeanConfiguration {

    private final EstoqueGateway estoqueGateway;

    public EstoqueBeanConfiguration(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
 }

    @Bean
    public SubtracaoDeEstoque subtracaoDeEstoque() {
        return new SubtracaoDeEstoque(estoqueGateway);
    }



}
