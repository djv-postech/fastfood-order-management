package com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.converter;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter {

    public Cliente convertFrom(ClienteEntity clienteEntity){
        return new Cliente( clienteEntity.getNome(), clienteEntity.getCpf(), clienteEntity.getEmail());
    }
}
