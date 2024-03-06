package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.CPF;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.vo.Email;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import static lombok.AccessLevel.PRIVATE;

@Component
@NoArgsConstructor(access = PRIVATE)
public class ClienteConverter {

    public static Cliente from(ClienteEntity clienteEntity){
        return new Cliente(clienteEntity.getNome(), new CPF(clienteEntity.getCpf()), new Email(clienteEntity.getEmail()));
    }
}

