package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.TipoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPagamento(
    String id,
    BigDecimal totalPagamento,
    TipoPagamento tipoPagamento,
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime dataPagamento,
    StatusPagamento statusPagamento) {

  public DadosPagamento(Pagamento dadosPagamento) {
    this(
        dadosPagamento.getId(),
        dadosPagamento.getTotalPagamento(),
        dadosPagamento.getTipoPagamento(),
        dadosPagamento.getDataEHorarioPagamento(),
        dadosPagamento.getStatusPagamento());
  }
}
