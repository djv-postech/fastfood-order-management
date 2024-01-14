package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email;

public class  Cliente {

  private String nome;
  private CPF cpf;
  private Email email;

  public Cliente(String nome, CPF cpf, Email email) {
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf.getNumero();
  }

  public String getEmail() {
    return email.getEndereco();
  }

  public void setEmail(Email email){
    this.email = email;
  }
}
