package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public record DadosCadastroPedido(List<DadosProduto> produtos,

                                  @JsonInclude(NON_NULL) DadosCliente cliente, StatusPedido status,

                                  @JsonSerialize(using = LocalDateTimeSerializer.class)
                                  @JsonInclude(NON_NULL) LocalDateTime dataCriacaoPedido,


                                  @NotNull BigDecimal valorTotal) {


    public Pedido convertToPedido() {
        return new Pedido(new Cliente(cliente.nome(), new CPF(cliente.cpf()), new Email(cliente.email())), buildProdutos(produtos), valorTotal,
                null,
                status, dataCriacaoPedido);
    }

    private List<Produto> buildProdutos(List<DadosProduto> cadastroProdutos) {
        return cadastroProdutos.stream().map(cadastroProduto -> new Produto(cadastroProduto.id(), cadastroProduto.nome(), cadastroProduto.descricao(), cadastroProduto.categoria(), cadastroProduto.preco(), cadastroProduto.quantidade())).collect(Collectors.toList());
    }


}
