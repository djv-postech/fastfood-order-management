package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;


import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;

public class ExclusaoDeCliente {
    private final ClienteRepository clienteRepository;


    public ExclusaoDeCliente(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public void excluirPorCpf(String cpf) {
        clienteRepository.excluirClientePorCpf(cpf);
    }
}
