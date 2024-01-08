package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos", description = "Rest api para operações de pedidos")
public class PedidoController {


    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido) {

       //Cadastra cliente
        dadosCadastroPedido.cliente();

        // Envia pedido para fila rabbit (será consumida pelo serviço produção)
     //   Pedido pedido =
       //         cadastroDePedido.cadastrarPedido(dadosCadastroPedido.convertToPedido());

        // Chama serviço de pagamento parar gerar QRCode
      //  final String qrCode = criacaoDePagamento.gerarQrCodeParaPagamento(pedidoCadastrado);

        //final Pagamento pagamento = pedidoCadastrado.getPagamento();

     //   atualizacaoDePagamento.atualizarPagamento(pagamento, pedidoCadastrado.getValorTotal());
       // atualizacaoDePedido.atualizarPedido(pedidoCadastrado.getNumeroPedido(), pagamento);

      //DadosPedido dadosPedido = new DadosPedido(pedidoCadastrado, qrCode);

        return ResponseEntity.ok().body(null);
    }

}
