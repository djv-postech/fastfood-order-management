package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableFeignClients(basePackages = {"com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign"})
public class FeignConfiguration {}
