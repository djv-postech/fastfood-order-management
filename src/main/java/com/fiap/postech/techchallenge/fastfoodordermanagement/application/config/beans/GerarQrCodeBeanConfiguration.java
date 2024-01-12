package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GerarQrCodeBeanConfiguration {

    private final PagamentoGateway pagamentoGateway;

    public GerarQrCodeBeanConfiguration(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
 }

    @Bean
    public GerarQrCode gerarQrCode() {
        return new GerarQrCode(pagamentoGateway);
    }

}
