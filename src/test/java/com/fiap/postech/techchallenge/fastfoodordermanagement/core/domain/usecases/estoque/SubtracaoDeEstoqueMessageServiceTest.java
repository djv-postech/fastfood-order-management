package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SubtracaoDeEstoqueMessageServiceTest {
    @Mock
    private RabbitTemplate rabbitTemplate;
    private MockMvc mockMvc;

    private SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService;

    @BeforeEach
    public void init() {
        subtracaoDeEstoqueMessageService = new SubtracaoDeEstoqueMessageService(rabbitTemplate);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subtracaoDeEstoqueMessageService).build();
    }

    @DisplayName("Test - Deve gerar subtração de estoque")
    @Test
    public void deveGerarSubtracaoDeEstoque() {
        // Dado
        List<Produto> produtos = List.of(ProdutoHelper.gerarProduto());

        // Quando
        subtracaoDeEstoqueMessageService.subtrairEstoque(produtos);

        // Entao
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyList());
    }
}