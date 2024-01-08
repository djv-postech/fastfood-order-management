package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.TipoPagamento;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosCadastroPagamento(
    @NotNull TipoPagamento tipoPagamento,
    @NotNull BigDecimal totalPagamento,
    @NotNull @JsonSerialize(using = LocalDateTimeSerializer.class) LocalDateTime dataPagamento,
    @NotNull StatusPagamento statusPagamento) {}
