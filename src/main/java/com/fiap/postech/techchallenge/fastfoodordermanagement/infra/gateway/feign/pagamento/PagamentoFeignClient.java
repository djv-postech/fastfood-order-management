package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "fastfood-payment",
        url = "${apis.fastfood.payment.client.url}",
        configuration = PagamentoClientConfig.class)
public interface PagamentoFeignClient {

    @PostMapping()
    ResponseEntity<String> gerarPagamento(@Valid @RequestBody DadosPedido dadosPedido);

}

