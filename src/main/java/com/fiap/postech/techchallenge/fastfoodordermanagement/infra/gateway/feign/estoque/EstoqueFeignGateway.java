package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public void subtrairEstoque(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto) {
        feignClient.subtrairEstoque(dadosSubtracaoEstoqueProduto);

    }
}
