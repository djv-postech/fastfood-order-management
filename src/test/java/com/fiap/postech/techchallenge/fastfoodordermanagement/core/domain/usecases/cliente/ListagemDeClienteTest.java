package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListagemDeClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    private ListagemDeCliente listagemDeCliente;

    @BeforeEach
    public void init() {
        listagemDeCliente = new ListagemDeCliente(clienteRepository);
        MockMvcBuilders.standaloneSetup(listagemDeCliente).build();
    }

    @DisplayName("Test - Deve listar todos clientes")
    @Test
    public void deveListarTodosClientes() {
        // Dado
        List<Cliente> clientes = List.of(PedidoHelper.gerarDadosPedido().cliente().convertToCliente());

        // Quando
        listagemDeCliente.todos();

        // Entao
        verify(clienteRepository, times(1)).listarClientes();
    }

}