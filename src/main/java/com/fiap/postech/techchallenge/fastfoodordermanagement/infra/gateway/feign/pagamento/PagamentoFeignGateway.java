package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento.exception.QrCodePagamentoException;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class PagamentoFeignGateway implements PagamentoGateway {

    private final PagamentoFeignClient feignClient;

    private static final String REQUEST = ", Request: ";
    private static final String RESPONSE = ", Response: ";

    public PagamentoFeignGateway(PagamentoFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public String gerarQrCode(DadosPedido dadosPedido) {
        try {
            return feignClient.gerarPagamento(dadosPedido);
        } catch (FeignException feignException) {
            String message =
                    feignException.getMessage()
                            .concat(REQUEST)
                            .concat(dadosPedido.toString())
                            .concat(RESPONSE)
                            .concat(feignException.contentUTF8());
            throw new QrCodePagamentoException(message);
        }
    }
}