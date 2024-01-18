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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CadastroDeClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    private CadastroDeCliente cadastroDeCliente;

    @BeforeEach
    public void init() {
        cadastroDeCliente = new CadastroDeCliente(clienteRepository);
        MockMvcBuilders.standaloneSetup(cadastroDeCliente).build();
    }

    @DisplayName("Test - Deve cadastrar cliente")
    @Test
    public void deveCadastrarCliente() {
        // Dado
        Cliente cliente = PedidoHelper.gerarDadosPedido().cliente().convertToCliente();

        // Quando
        cadastroDeCliente.cadastrar(cliente);

        // Entao
        verify(clienteRepository, times(1)).cadastrarCliente(cliente);
    }

}