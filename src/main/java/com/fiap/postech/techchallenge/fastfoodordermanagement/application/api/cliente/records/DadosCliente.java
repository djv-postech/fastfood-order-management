package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.cliente.records;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;

public record DadosCliente(String nome, String cpf, String email) {

  public DadosCliente(Cliente dadosCliente) {
    this(dadosCliente.getNome(), dadosCliente.getCpf(), dadosCliente.getEmail());
  }
}
