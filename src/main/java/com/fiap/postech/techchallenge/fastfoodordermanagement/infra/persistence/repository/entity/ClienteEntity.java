package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClienteEntity {


    private String nome;

    @Id
    private  String cpf;

    private String email;

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public ClienteEntity(){

    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public static ClienteEntity from(Cliente cliente) {
        return new ClienteEntity(cliente.getNome(),
                cliente.getCpf(),
                cliente.getEmail());
    }
}
