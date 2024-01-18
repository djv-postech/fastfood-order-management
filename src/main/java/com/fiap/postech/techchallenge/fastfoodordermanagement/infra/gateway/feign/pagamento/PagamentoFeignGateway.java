package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import org.springframework.stereotype.Service;

@Service
public class PagamentoFeignGateway implements PagamentoGateway {

    private final PagamentoFeignClient feignClient;

    public PagamentoFeignGateway(PagamentoFeignClient feignClient) {
        this.feignClient = feignClient;
    }


    @Override
    public String gerarQrCode(DadosPedido dadosPedido) {
        return feignClient.gerarPagamento(dadosPedido);
    }
}
