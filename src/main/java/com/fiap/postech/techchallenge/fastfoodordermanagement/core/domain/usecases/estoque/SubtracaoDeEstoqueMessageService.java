package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.config.amqp.ProducaoAMQPConfiguration.PRODUTO_ESTOQUE_EX;
import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter.ProdutoEstoqueConverter.convertFrom;

public class SubtracaoDeEstoqueMessageService {

    private final RabbitTemplate rabbitTemplate;

    public SubtracaoDeEstoqueMessageService(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void subtrairEstoque(List<Produto> produtos) {
        rabbitTemplate.convertAndSend(PRODUTO_ESTOQUE_EX, "", convertFrom(produtos));
    }
}
