package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "estoque",
        url = "${estoque.client.url}")
public interface EstoqueFeignClient {
    @PostMapping("/estoque/produto/{id}")
    void subtrairEstoque(

            @PathVariable("id") String id,
            @RequestParam("quantidade") Integer quantidade);

    }

