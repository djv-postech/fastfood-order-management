package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;

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
public class  IdentificacaoDeClienteTest {

    @Mock
    private ClienteRepository clienteRepository;

    private IdentificacaoDeCliente identificacaoDeCliente;

    @BeforeEach
    public void init() {
        identificacaoDeCliente = new IdentificacaoDeCliente(clienteRepository);
        MockMvcBuilders.standaloneSetup(identificacaoDeCliente).build();
    }

    @DisplayName("Test - Deve identificar cliente por cpf")
    @Test
    public void deveIdentificarClientePorCPF() {
        // Dado
        String cpf = "123.456.789-01";

        // Quando
        identificacaoDeCliente.identificarPorCpf(cpf);

        // Entao
        verify(clienteRepository, times(1)).identificaClientePorCpf(cpf);
    }
}

