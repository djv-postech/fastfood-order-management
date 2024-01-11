package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;

import java.util.List;

public interface ClienteRepository {

  Cliente cadastrarCliente(Cliente cliente);

  Cliente identificaClientePorCpf(String cpf);

  Cliente atualizarCliente(Cliente cliente);

  List<Cliente> listarClientes();

  void excluirClientePorCpf(String cpf);
}
