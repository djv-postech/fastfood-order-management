package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.ClienteRepositoryMysql;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter.ClienteConverter;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClienteRespositoryImpl implements ClienteRepository {

    private final ClienteRepositoryMysql clienteRepositoryMysql;
    private final ClienteConverter clienteConverter;

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {

        return clienteConverter.from(clienteRepositoryMysql.save(ClienteEntity.from(cliente)));
    }

    @Override
    public Cliente identificaClientePorCpf(String cpf) {

        Optional<ClienteEntity> clienteEntity = clienteRepositoryMysql.findByCpf(cpf);
        return clienteEntity.map(clienteConverter::from).orElseThrow(
                () -> new NotFoundException("Cliente de CPF: " + cpf + " não encontrado"));
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        return null;
    }

    @Override
    public void excluirClientePorCpf(String cpf) {

    }
}
