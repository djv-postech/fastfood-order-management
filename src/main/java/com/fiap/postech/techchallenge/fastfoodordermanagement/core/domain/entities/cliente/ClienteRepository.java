package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente;

public interface ClienteRepository {

  void cadastrarCliente(Cliente cliente);

  Cliente identificaClientePorCpf(String cpf);

}
