package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.ClienteEncoder;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.ClienteRepositoryMysql;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter.ClienteConverter;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fiap.postech.techchallenge.fastfoodordermanagement.infra.ClienteEncoder.*;

@RequiredArgsConstructor
@Component
public class ClienteRespositoryImpl implements ClienteRepository {

    private final ClienteRepositoryMysql clienteRepositoryMysql;
    private final ClienteConverter clienteConverter;

    @Override
    public void cadastrarCliente(Cliente cliente) {
        clienteRepositoryMysql.save(encode(ClienteEntity.from(cliente)));
    }

    @Override
    public Cliente identificaClientePorCpf(String cpf) {

        String cpfCriptografado = criptografar(cpf);

        Optional<ClienteEntity> clienteEntity = clienteRepositoryMysql.findByCpf(cpfCriptografado);

        return clienteEntity
                .map(ClienteEncoder::decode)
                .map(clienteConverter::from)
                .orElseThrow(() -> new NotFoundException(
                        "Cliente de CPF: " + cpf + " não encontrado"));
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepositoryMysql.findAll().stream()
            .map(ClienteEncoder::decode)
            .map(clienteConverter::from)
            .collect(Collectors.toList());
    }

    @Override
    public void excluirClientePorCpf(String cpf) {
        String cpfCriptografado = criptografar(cpf);
        clienteRepositoryMysql.deleteById(cpfCriptografado);
    }
}
