package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;

import java.util.UUID;

public class GerarNumeroDoPedido {

    public Pedido gerar(Pedido pedido){
        pedido.setNumeroPedido(UUID.randomUUID().toString());
        return pedido;
    }
}
