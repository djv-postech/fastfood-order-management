package com.fiap.postech.techchallenge.fastfoodordermanagement;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoHelper {

    public static DadosPagamento gerarDadosPagamento() {
        return new DadosPagamento("1", new BigDecimal(10), TipoPagamento.QRCODE, LocalDateTime.now(), StatusPagamento.PROCESSANDO);
    }

    public static Pagamento gerarPagamento() {
        return new Pagamento("1", new BigDecimal(10), TipoPagamento.QRCODE, LocalDateTime.now(), StatusPagamento.PROCESSANDO);
    }

}
