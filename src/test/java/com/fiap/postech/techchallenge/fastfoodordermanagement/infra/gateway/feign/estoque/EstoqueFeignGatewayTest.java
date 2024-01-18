package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque;

import com.fiap.postech.techchallenge.fastfoodordermanagement.ProdutoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosSubtracaoEstoqueProduto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception.SubtracaoDeEstoqueException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstoqueFeignGatewayTest {
    @Mock
    private EstoqueFeignClient feignClient;

    private EstoqueFeignGateway estoqueFeignGateway;

    @BeforeEach
    public void init() {
        estoqueFeignGateway = new EstoqueFeignGateway(feignClient);
        MockMvcBuilders.standaloneSetup(estoqueFeignGateway).build();
    }

    @DisplayName("Test - Deve subtrair estoque")
    @Test
    public void deveSubtrairEstoque() {
        // Dado
        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto = List.of(ProdutoHelper.gerarDadosSubtracaoEstoqueProduto());

        // Quando
        estoqueFeignGateway.subtrairEstoque(dadosSubtracaoEstoqueProduto);

        // Entao
        verify(feignClient, times(1)).subtrairEstoque(dadosSubtracaoEstoqueProduto);
    }

    @DisplayName("Test - Deve retornar SubtracaoDeEstoqueException quando estoque retornar erro")
    @Test
    public void deveRetornarSubtracaoDeEstoqueExceptionQuandoEstoqueRetornarErro() {
        // Dado
        List<DadosSubtracaoEstoqueProduto> dadosSubtracaoEstoqueProduto = List.of(ProdutoHelper.gerarDadosSubtracaoEstoqueProduto());
        Map<String, Collection<String>> headers = new HashMap<>();
        doThrow(FeignException.errorStatus("any", Response.builder()
                .status(500)
                .request(Request.create(Request.HttpMethod.POST, "url", headers, "".getBytes(), StandardCharsets.UTF_8))
                .build()))
                .when(feignClient)
                .subtrairEstoque(dadosSubtracaoEstoqueProduto);

        // Quando
        // Entao
        Assertions.assertThrows(
                SubtracaoDeEstoqueException.class, () -> estoqueFeignGateway.subtrairEstoque(dadosSubtracaoEstoqueProduto));
        verify(feignClient, times(1)).subtrairEstoque(dadosSubtracaoEstoqueProduto);

    }
}