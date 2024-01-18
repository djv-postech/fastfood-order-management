package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.EstoqueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SubtracaoDeEstoqueTest {

    @Mock
    private EstoqueGateway estoqueGateway;
    private MockMvc mockMvc;

    private SubtracaoDeEstoque subtracaoDeEstoque;

    @BeforeEach
    public void init() {
        subtracaoDeEstoque = new SubtracaoDeEstoque(estoqueGateway);
        this.mockMvc = MockMvcBuilders.standaloneSetup(subtracaoDeEstoque).build();
    }

    @DisplayName("Test - Deve subtrair estoque do produto")
    @Test
    public void deveSubtrairEstoque() {
        // Dado
        List<Produto> produtos = List.of(ProdutoHelper.gerarProduto());

        // Quando
        subtracaoDeEstoque.subtrair(produtos);

        // Entao
        verify(estoqueGateway, times(1)).subtrairEstoque(any());

    }

}