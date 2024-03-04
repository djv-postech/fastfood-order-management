package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.SolicitacaoDePagamentoMessageService;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.PagamentoGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GerarQrCodeBeanConfiguration {

    private final PagamentoGateway pagamentoGateway;
    private final RabbitTemplate rabbitTemplate;

    public GerarQrCodeBeanConfiguration(PagamentoGateway pagamentoGateway, RabbitTemplate rabbitTemplate) {
        this.pagamentoGateway = pagamentoGateway;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Bean
    public GerarQrCode gerarQrCode() {
        return new GerarQrCode(pagamentoGateway);
    }

    @Bean
    public SolicitacaoDePagamentoMessageService solicitacaoDePagamentoMessageService() {
        return new SolicitacaoDePagamentoMessageService(rabbitTemplate);
    }

}
