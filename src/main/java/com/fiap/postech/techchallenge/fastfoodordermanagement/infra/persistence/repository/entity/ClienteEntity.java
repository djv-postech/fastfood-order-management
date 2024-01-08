package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email;

public class ClienteEntity {
    private String nome;
    private  CPF cpf;
    private Email email;

    public ClienteEntity(String nome, CPF cpf, Email email) {
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

    public CPF getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email){
        this.email = email;
    }
}
