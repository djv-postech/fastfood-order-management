package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "product.client")
public class EstoqueClientProperties {
  private int connectTimeout = 10000;

  private int readTimeout = 60000;


}
