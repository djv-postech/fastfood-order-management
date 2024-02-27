package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Endereco {

    private String rua;
    private String complemento;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco(String rua, String complemento, String numero, String bairro, String cidade, String cep) {
        this.rua = rua;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }
}
