package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CadastroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.ExclusaoDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.IdentificacaoDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.ListagemDeCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.PedidoControllerTest.convertToJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    @Mock
    private CadastroDeCliente cadastroDeCliente;

    @Mock
    private ListagemDeCliente listagemDeCliente;

    @Mock
    private IdentificacaoDeCliente identificacaoDeCliente;

    @Mock
    private ExclusaoDeCliente exclusaoDeCliente;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        ClienteController clienteController = new ClienteController(cadastroDeCliente, listagemDeCliente, identificacaoDeCliente, exclusaoDeCliente);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @DisplayName("Test - Deve cadastrar novo cliente")
    @Test
    public void deveCadastrarNovoCliente() throws Exception {
        // Dado
        DadosPedido dadosPedido = PedidoHelper.gerarDadosPedido();
        DadosCliente dadosCliente = PedidoHelper.gerarDadosCadastroPedido().cliente();

        // Quando
        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(dadosCliente)))
                .andExpect(status().isOk());

        // Entao
        verify(cadastroDeCliente, times(1))
                .cadastrar(any(Cliente.class));

    }

    @DisplayName("Test - Deve identificar cliente pelo CPF")
    @Test
    public void deveIdentificarClientePeloCpf() throws Exception {
        // Dado
        String cpf = "123.456.789-01";
        Cliente cliente = PedidoHelper.gerarDadosCadastroPedido().cliente().convertToCliente();
        when(identificacaoDeCliente.identificarPorCpf(cpf)).thenReturn(cliente);

        // Quando
        mockMvc.perform(get("/cliente/123.456.789-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Entao
        verify(identificacaoDeCliente, times(1))
                .identificarPorCpf(cpf);

    }

    @DisplayName("Test - Deve listar clientes")
    @Test
    public void deveListarClientes() throws Exception {
        // Dado
        List<Cliente> clientes = List.of(PedidoHelper.gerarDadosCadastroPedido().cliente().convertToCliente());
        when(listagemDeCliente.todos()).thenReturn(clientes);

        // Quando
        mockMvc.perform(get("/cliente/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Entao
        verify(listagemDeCliente, times(1))
                .todos();
    }

    @DisplayName("Test - Deve remover cliente por CPF")
    @Test
    public void deveRemoverClientePorCpf() throws Exception {
        // Dado
        String cpf = "123.456.789-01";

        // Quando
        mockMvc.perform(delete("/cliente/123.456.789-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Entao
        verify(exclusaoDeCliente, times(1))
                .excluirPorCpf(cpf);
    }

}