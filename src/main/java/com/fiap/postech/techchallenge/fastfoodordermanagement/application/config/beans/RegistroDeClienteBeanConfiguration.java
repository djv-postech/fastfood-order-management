package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.ClienteRepository;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CadastroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistroDeClienteBeanConfiguration {

    private final ClienteRepository clienteRepository;
    private final CadastroDeCliente cadastroDeCliente;

    public RegistroDeClienteBeanConfiguration(ClienteRepository clienteRepository, CadastroDeCliente cadastroDeCliente) {
        this.clienteRepository = clienteRepository;
        this.cadastroDeCliente = cadastroDeCliente;
    }


    @Bean
    public RegistroDeCliente registroDeCliente(){
        return new RegistroDeCliente(clienteRepository,  cadastroDeCliente);
    }
}
