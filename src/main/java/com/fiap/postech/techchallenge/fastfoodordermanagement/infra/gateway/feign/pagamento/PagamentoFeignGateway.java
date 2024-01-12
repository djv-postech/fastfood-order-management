package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import org.springframework.stereotype.Service;

@Service
public class PagamentoFeignGateway implements PagamentoGateway {

    private final PagamentoFeignClient feignClient;

    private final PagamentoClientProperties properties;

    public PagamentoFeignGateway(PagamentoFeignClient feignClient, PagamentoClientProperties properties) {
        this.feignClient = feignClient;
        this.properties = properties;
    }

    @Override
    public String gerarQrCode(DadosPedido dadosPedido) {
        //FIXME
        return feignClient.gerarPagamento(dadosPedido).toString();
    }
}
