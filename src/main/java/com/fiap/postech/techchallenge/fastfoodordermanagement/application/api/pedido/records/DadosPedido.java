package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.Objects.isNull;

public record DadosPedido(
    String id,
    List<DadosProduto> produtos,

    @JsonInclude(NON_NULL) DadosCliente cliente,
    DadosPagamento pagamento,
    StatusPedido status,
    LocalDateTime dataCriacaoPedido,

    @JsonInclude(NON_NULL) String qrCode) {

  public DadosPedido(Pedido dadosPedido, String qrCode) {
    this(
        dadosPedido.getNumeroPedido(),
        dadosPedido.getProdutos().stream().map(DadosProduto::new).collect(
            Collectors.toList()),
        isNull(dadosPedido.getCliente())? null: new DadosCliente(dadosPedido.getCliente()),
        new DadosPagamento(dadosPedido.getPagamento()),
        dadosPedido.getStatusPedido(),
        dadosPedido.getDataCriacaoPedido(), qrCode);
  }

  public DadosPedido(Pedido dadosPedido) {
    this(
        dadosPedido.getNumeroPedido(),
        dadosPedido.getProdutos().stream().map(DadosProduto::new).collect(
            Collectors.toList()),
        isNull(dadosPedido.getCliente())? null: new DadosCliente(dadosPedido.getCliente()),
        new DadosPagamento(dadosPedido.getPagamento()),
        dadosPedido.getStatusPedido(),
        dadosPedido.getDataCriacaoPedido(), null);
  }
}
