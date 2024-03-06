package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.ClienteRepositoryMysql;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter.ClienteConverter;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.webjars.NotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteRespositoryImplTest {

    @Mock
    private ClienteRepositoryMysql clienteRepositoryMysql;

    private ClienteRespositoryImpl clienteRespository;


    @BeforeEach
    public void init() {
        clienteRespository = new ClienteRespositoryImpl(clienteRepositoryMysql);
        MockMvcBuilders.standaloneSetup(clienteRespository).build();
    }

//    @DisplayName("Test - Deve cadastrar cliente")
//    @Test
//    public void deveCadastrarCliente() {
//        // Dado
//        Cliente cliente = PedidoHelper.gerarDadosPedido().cliente().convertToCliente();
//        ClienteEntity clienteEntity = ClienteEntity.from(cliente);
//        when(clienteRepositoryMysql.save(any())).thenReturn(clienteEntity);
//
//        // Quando
//        clienteRespository.cadastrarCliente(cliente);
//
//        // Entao
//        verify(clienteRepositoryMysql, times(1)).save(any());
//    }
//
//    @DisplayName("Test - Deve identificar cliente por CPF")
//    @Test
//    public void deveIdentificarClientePorCpf() {
//        // Dado
//        Cliente cliente = PedidoHelper.gerarDadosPedido().cliente().convertToCliente();
//        ClienteEntity clienteEntity = ClienteEntity.from(cliente);
//        when(clienteRepositoryMysql.findByCpf(anyString())).thenReturn(Optional.of(clienteEntity));
//
//        // Quando
//        Cliente clienteIdentificado = clienteRespository.identificaClientePorCpf(cliente.getCpf());
//
//        // Entao
//        verify(clienteRepositoryMysql, times(1)).findByCpf(any());
//        Assertions.assertNotNull(clienteIdentificado);
//    }
//
//    @DisplayName("Test - Deve retornar exception quando cliente não é encontrado")
//    @Test
//    public void deveRetornarNotFoundExceptionQuandoClienteNaoEstaCadastrado() {
//        // Dado
//        String cpf = "123.456.789-01";
//        when(clienteRepositoryMysql.findByCpf(cpf)).thenThrow(NotFoundException.class);
//
//        // Quando
//        // Entao
//        Assertions.assertThrows(
//                NotFoundException.class, () -> clienteRespository.identificaClientePorCpf(cpf));
//        verify(clienteRepositoryMysql, times(1)).findByCpf(any());
//
//    }
}