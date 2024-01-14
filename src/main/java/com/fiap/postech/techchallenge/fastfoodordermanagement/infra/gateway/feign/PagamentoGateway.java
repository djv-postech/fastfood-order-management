package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;

public interface PagamentoGateway {

    String gerarQrCode(DadosPedido dadosPedido);
}
