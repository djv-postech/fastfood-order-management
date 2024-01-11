package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;

public class CadastroDeCliente {
    private final ClienteRepository clienteRepository;

    public CadastroDeCliente(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente) {
        //FIXME: Antes de cadastrar, verificar se ja existe
        return this.clienteRepository.cadastrarCliente(cliente);
    }
}