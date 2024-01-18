package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento;

import feign.Request.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Request.Options;

@Configuration
@RequiredArgsConstructor
public class PagamentoClientConfiguration {
    private final PagamentoClientProperties properties;

    @Bean("pagamentoClient")
    public Options options() {
        return new Options(properties.getConnectTimeout(), properties.getReadTimeout());
    }
}

