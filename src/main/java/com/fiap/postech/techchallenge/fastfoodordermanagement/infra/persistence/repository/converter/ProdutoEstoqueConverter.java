package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@Component
@NoArgsConstructor(access = PRIVATE)
public class ProdutoEstoqueConverter {

    public static List<DadosSubtracaoEstoqueProduto> convertFrom(List<Produto> produtos) {
        return produtos.stream().map(produto -> new DadosSubtracaoEstoqueProduto(produto.getId(), produto.getQuantidade())).collect(Collectors.toList());
    }
}
