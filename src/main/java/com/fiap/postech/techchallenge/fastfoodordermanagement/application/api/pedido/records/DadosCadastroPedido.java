package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.Pagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.StatusPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosCadastroPedido(
        DadosCadastroCliente cliente,
        @NotNull List<DadosCadastroProduto> produtos,
        @NotNull BigDecimal valorTotal,
        //@NotNull
        DadosCadastroPagamento pagamento,
        @NotNull StatusPedido statusPedido,
        @NotNull @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime dataCriacaoPedido) {

    public Pedido convertToPedido() {
        return new Pedido(
                new Cliente(cliente.nome(), new CPF(cliente.cpf()), new Email(cliente.email())),
                buildProdutos(produtos),
                valorTotal,
                new Pagamento(
                        pagamento.dataPagamento(),
                        pagamento.statusPagamento(),
                        pagamento.tipoPagamento(),
                        pagamento.totalPagamento()),
                statusPedido,
                dataCriacaoPedido);
    }

    private List<Produto> buildProdutos(List<DadosCadastroProduto> cadastroProdutos) {
        return cadastroProdutos.stream()
                .map(
                        cadastroProduto ->
                                new Produto(
                                        cadastroProduto.id(),
                                        cadastroProduto.nome(),
                                        cadastroProduto.descricao(),
                                        cadastroProduto.categoria(),
                                        cadastroProduto.preco(),
                                        cadastroProduto.quantidade()))
                .collect(Collectors.toList());
    }
}
