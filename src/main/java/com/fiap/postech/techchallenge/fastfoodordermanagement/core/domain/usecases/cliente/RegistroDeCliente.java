package com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente;


import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;
import org.webjars.NotFoundException;

public class RegistroDeCliente {

    private final ClienteRepository clienteRepository;

    private final CadastroDeCliente cadastroDeCliente;

    public RegistroDeCliente(ClienteRepository clienteRepository, CadastroDeCliente cadastroDeCliente){
        this.clienteRepository = clienteRepository;
        this.cadastroDeCliente = cadastroDeCliente;
    }

    public void registrar(Cliente cliente){
        try {
            clienteRepository.identificaClientePorCpf(cliente.getCpf());
        } catch (NotFoundException exception){
            cadastroDeCliente.cadastrar(cliente);
        }
    }
}
