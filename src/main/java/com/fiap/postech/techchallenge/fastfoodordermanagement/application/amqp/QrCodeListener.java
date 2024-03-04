package com.fiap.postech.techchallenge.fastfoodordermanagement.application.amqp;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QrCodeListener {
    public static final String QR_CODE_QUEUE = "queue.qr_code";

    private final AtualizacaoDePedido atualizacaoDePedido;

    private final CriacaoDePedido criacaoDePedido;

    public QrCodeListener(AtualizacaoDePedido atualizacaoDePedido, CriacaoDePedido criacaoDePedido) {
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.criacaoDePedido = criacaoDePedido;
    }

    @RabbitListener(queues = QR_CODE_QUEUE)
    public void cadastrarPedido(DadosPedido dadosPedido){
        String qrCode = dadosPedido.convertToPedido().getQrCode();

        log.info("QR Code recebido! Payload: {}", dadosPedido);

        atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido(), qrCode);

        criacaoDePedido.criar(dadosPedido);
    }

}
