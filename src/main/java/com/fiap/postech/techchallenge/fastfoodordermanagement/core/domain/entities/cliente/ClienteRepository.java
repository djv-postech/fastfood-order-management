package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente;

import java.util.List;

public interface ClienteRepository {

  void cadastrarCliente(Cliente cliente);

  Cliente identificaClientePorCpf(String cpf);

  List<Cliente> listarClientes();

  void excluirClientePorCpf(String cpf);
}
