package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;


import feign.Request.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EstoqueClientConfig {
  private final EstoqueClientProperties properties;

  @Bean("estoqueClient")
  public Options options() {
    return new Options(properties.getConnectTimeout(), properties.getReadTimeout());
  }
}
