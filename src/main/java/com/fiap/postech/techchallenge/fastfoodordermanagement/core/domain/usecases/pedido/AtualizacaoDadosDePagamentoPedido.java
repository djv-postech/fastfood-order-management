package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.TipoPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizacaoDadosDePagamentoPedido {

    public Pedido atualizarPedido(Pedido pedido){

        Pagamento pagamento = new Pagamento(UUID.randomUUID().toString(), pedido.getValorTotal(), TipoPagamento.QRCODE, LocalDateTime.now(), StatusPagamento.PROCESSANDO);
        pedido.setPagamento(pagamento);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        return pedido;
    }
}
