package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;

public record DadosCliente(String nome, String cpf, String email) {

  public DadosCliente(Cliente dadosCliente) {
    this(dadosCliente.getNome(), dadosCliente.getCpf(), dadosCliente.getEmail());
  }

  public Cliente convertToCliente() {
    return new Cliente(nome, new com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF(cpf),
            new com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email(email));
  }
}
