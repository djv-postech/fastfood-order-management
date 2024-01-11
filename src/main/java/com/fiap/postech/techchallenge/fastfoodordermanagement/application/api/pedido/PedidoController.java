package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.produto.Produto;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CadastroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos", description = "Rest api para operações de pedidos")
public class PedidoController {

    private final CadastroDeCliente cadastroDeCliente;

    private final CriacaoDePedido criacaoDePedido;

    private final SubtracaoDeEstoque subtracaoDeEstoque;

    public PedidoController(CadastroDeCliente cadastroDeCliente, CriacaoDePedido criacaoDePedido, SubtracaoDeEstoque subtracaoDeEstoque) {
        this.cadastroDeCliente = cadastroDeCliente;
        this.criacaoDePedido = criacaoDePedido;
        this.subtracaoDeEstoque = subtracaoDeEstoque;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido) {

        // Criar use case para pedido

        //Cadastra cliente
        cadastroDeCliente.cadastrar(dadosCadastroPedido.cliente().convertToCliente());

        // Chamada para debitar estoque
        List<Produto> produtos = dadosCadastroPedido.convertToPedido().getProdutos();
        subtracaoDeEstoque.subtrair(produtos);


       //   Pedido pedido =
        //         cadastroDePedido.cadastrarPedido(dadosCadastroPedido.convertToPedido());

        // Chama serviço de pagamento parar gerar QRCode, chama use case para atualizar servico
        //  final String qrCode = criacaoDePagamento.gerarQrCodeParaPagamento(pedidoCadastrado);

        // Envia pedido para fila rabbit (será consumida pelo serviço produção)
        criacaoDePedido.criar(dadosCadastroPedido);


        return ResponseEntity.ok().body(null);
    }

}
