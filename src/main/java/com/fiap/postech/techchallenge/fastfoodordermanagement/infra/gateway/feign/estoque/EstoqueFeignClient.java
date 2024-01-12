package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(
        name = "estoque",
        url = "${estoque.client.url}",
        configuration = EstoqueClientConfig.class)
public interface EstoqueFeignClient {
    @PostMapping("/produto")
    void subtrairEstoque(

           @RequestBody List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto);

    }

