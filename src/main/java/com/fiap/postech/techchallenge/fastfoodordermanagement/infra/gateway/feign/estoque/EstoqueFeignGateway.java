package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception.SubtracaoDeEstoqueException;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstoqueFeignGateway implements EstoqueGateway {

    private final EstoqueFeignClient feignClient;

    private static final String REQUEST = ", Request: ";
    private static final String RESPONSE = ", Response: ";

    public EstoqueFeignGateway(EstoqueFeignClient feignClient) {
        this.feignClient = feignClient;
    }


    @Override
    public void subtrairEstoque(List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto) {
        try {
            feignClient.subtrairEstoque(dadosSubtracaoEstoqueProduto);
        } catch (FeignException feignException){
            String message =
                    feignException.getMessage()
                            .concat(REQUEST)
                            .concat(dadosSubtracaoEstoqueProduto.toString())
                            .concat(RESPONSE)
                            .concat(feignException.contentUTF8());
            throw new SubtracaoDeEstoqueException(message);
        }

    }
}
