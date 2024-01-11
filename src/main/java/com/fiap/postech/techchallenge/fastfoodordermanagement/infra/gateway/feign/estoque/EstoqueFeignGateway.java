package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFeignGateway implements EstoqueGateway {

    private final EstoqueFeignClient feignClient;
    private final EstoqueClientProperties properties;

    public EstoqueFeignGateway(
            EstoqueFeignClient feignClient,
            EstoqueClientProperties properties
    ) {
        this.feignClient = feignClient;
        this.properties = properties;
    }

    @Override
    public void subtrairEstoque(String id, Integer quantidade) {
        feignClient.subtrairEstoque(id, quantidade);

    }
}
