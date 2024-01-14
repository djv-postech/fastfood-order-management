package com.fiap.postech.techchallenge.fastfoodordermanagement.application.config.beans;

import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.GerarNumeroDoPedido;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoBeanConfiguration {

    public  PedidoBeanConfiguration( ){
    }

    @Bean
    public CriacaoDePedido criacaoDePedido(){
        return new CriacaoDePedido();
    }

    @Bean
    public GerarNumeroDoPedido gerarNumeroDoPedido(){
        return new GerarNumeroDoPedido();
    }

    @Bean
    AtualizacaoDePedido atualizacaoDePedido(){ return new AtualizacaoDePedido();}
}
