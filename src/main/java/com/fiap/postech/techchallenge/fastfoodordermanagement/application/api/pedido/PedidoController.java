package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pagamento.StatusPagamento;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CadastroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.CriacaoDePedido;
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

    private final CadastroDeCliente cadastroDeCliente;

    private final CriacaoDePedido criacaoDePedido;

    private final SubtracaoDeEstoque subtracaoDeEstoque;

    private final AtualizacaoDePedido atualizacaoDePedido;

    private final GerarQrCode gerarQrCode;

    public PedidoController(CadastroDeCliente cadastroDeCliente, CriacaoDePedido criacaoDePedido, SubtracaoDeEstoque subtracaoDeEstoque, AtualizacaoDePedido atualizacaoDePedido, GerarQrCode gerarQrCode) {
        this.cadastroDeCliente = cadastroDeCliente;
        this.criacaoDePedido = criacaoDePedido;
        this.subtracaoDeEstoque = subtracaoDeEstoque;
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.gerarQrCode = gerarQrCode;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosPedido dadosPedido) {

        //FIXME Endpoint deve receber um dadosCadastroPedido ou dadosPedido? Tem que retornar um dados

        //FIXME Tratar erros
        // Criar use case para pedido

        //Cadastra cliente
        cadastroDeCliente.cadastrar(dadosPedido.cliente().convertToCliente());

        // Chamada para debitar estoque
        //      subtracaoDeEstoque.subtrair(dadosPedido.convertToPedido().getProdutos());

        // Chama serviço de pagamento parar gerar QRCode, chama use case para atualizar servico
        String qrCode = gerarQrCode.gerar(dadosPedido);

        //Atualizar pedido
        Pedido pedido = atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido(), qrCode);

        // Envia pedido para fila rabbit (será consumida pelo serviço produção)
        criacaoDePedido.criar(new DadosPedido(pedido));

        return ResponseEntity.ok().body(null);
    }

}
