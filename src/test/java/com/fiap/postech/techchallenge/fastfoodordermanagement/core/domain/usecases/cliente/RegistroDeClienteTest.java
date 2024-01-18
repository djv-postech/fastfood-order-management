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
import org.webjars.NotFoundException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistroDeClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CadastroDeCliente cadastroDeCliente;

    private RegistroDeCliente registroDeCliente;

    @BeforeEach
    public void init() {
        registroDeCliente = new RegistroDeCliente(clienteRepository, cadastroDeCliente);
        MockMvcBuilders.standaloneSetup(registroDeCliente).build();
    }

    @DisplayName("Test - Deve identificar cliente")
    @Test
    public void deveIdentificarCliente() {
        // Dado
        Cliente cliente = PedidoHelper.gerarDadosPedido().cliente().convertToCliente();
        when(clienteRepository.identificaClientePorCpf(cliente.getCpf())).thenReturn(cliente);

        // Quando
        registroDeCliente.registrar(cliente);

        // Entao
        verify(cadastroDeCliente, times(0)).cadastrar(cliente);
    }

    @DisplayName("Test - Deve cadastrar cliente")
    @Test
    public void deveIdentificarClienteCasoClienteNaoSejaEncontradoDeveCadastrarCliente() {
        // Dado
        Cliente cliente = PedidoHelper.gerarDadosPedido().cliente().convertToCliente();
        when(clienteRepository.identificaClientePorCpf(cliente.getCpf())).thenThrow(NotFoundException.class);

        // Quando
        registroDeCliente.registrar(cliente);

        // Entao
        verify(cadastroDeCliente, times(1)).cadastrar(cliente);
    }
}