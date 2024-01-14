package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
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

    private final RegistroDeCliente registroDeCliente;

    private final CriacaoDePedido criacaoDePedido;

    private final SubtracaoDeEstoque subtracaoDeEstoque;

    private final AtualizacaoDePedido atualizacaoDePedido;

    private final GerarQrCode gerarQrCode;

    public PedidoController(RegistroDeCliente registroDeCliente, CriacaoDePedido criacaoDePedido, SubtracaoDeEstoque subtracaoDeEstoque, AtualizacaoDePedido atualizacaoDePedido, GerarQrCode gerarQrCode) {
        this.registroDeCliente = registroDeCliente;
        this.criacaoDePedido = criacaoDePedido;
        this.subtracaoDeEstoque = subtracaoDeEstoque;
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.gerarQrCode = gerarQrCode;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosPedido dadosPedido) {

        registroDeCliente.registrar(dadosPedido.cliente().convertToCliente());

        subtracaoDeEstoque.subtrair(dadosPedido.convertToPedido().getProdutos());

        // Chama serviço de pagamento parar gerar QRCode, chama use case para atualizar servico
        String qrCode = gerarQrCode.gerar(dadosPedido);

        //Atualizar pedido
        Pedido pedido = atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido(), qrCode);

        // Envia pedido para fila rabbit (será consumida pelo serviço produção)
        criacaoDePedido.criar(new DadosPedido(pedido));

        return ResponseEntity.ok().body(null);
    }

}
