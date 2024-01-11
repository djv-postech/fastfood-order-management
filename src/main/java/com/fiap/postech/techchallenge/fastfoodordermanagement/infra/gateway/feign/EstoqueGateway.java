package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign;

public interface EstoqueGateway {

   void subtrairEstoque(String id, Integer quantidade);
}
