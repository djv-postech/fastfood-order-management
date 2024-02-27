package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;


import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;

import java.util.List;

public class ListagemDeCliente {

    private final ClienteRepository clienteRepository;

    public ListagemDeCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> todos() {
        return clienteRepository.listarClientes();
    }
}
