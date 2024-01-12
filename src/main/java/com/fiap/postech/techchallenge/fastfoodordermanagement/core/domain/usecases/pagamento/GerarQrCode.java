package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;

public class GerarQrCode {

    private final PagamentoGateway pagamentoGateway;

    public GerarQrCode(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public String gerar(DadosPedido dadosPedido){
       return pagamentoGateway.gerarQrCode(dadosPedido);
    }
}
