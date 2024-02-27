package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.CriacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoque;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.GerarQrCode;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.AtualizacaoDePedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pedido.GerarNumeroDoPedido;
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

    private final GerarNumeroDoPedido gerarNumeroDoPedido;

    public PedidoController(RegistroDeCliente registroDeCliente, CriacaoDePedido criacaoDePedido, SubtracaoDeEstoque subtracaoDeEstoque, AtualizacaoDePedido atualizacaoDePedido, GerarQrCode gerarQrCode, GerarNumeroDoPedido gerarNumeroDoPedido) {
        this.registroDeCliente = registroDeCliente;
        this.criacaoDePedido = criacaoDePedido;
        this.subtracaoDeEstoque = subtracaoDeEstoque;
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.gerarQrCode = gerarQrCode;
        this.gerarNumeroDoPedido = gerarNumeroDoPedido;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(@Valid @RequestBody DadosCadastroPedido dadosCadastroPedido) {

        registroDeCliente.registrar(dadosCadastroPedido.cliente().convertToCliente());

        subtracaoDeEstoque.subtrair(dadosCadastroPedido.convertToPedido().getProdutos());

        //TODO: APos gerar pedido mesmo sem QRCODE, teremos q gravar o pedido, pq quando tivermos o qrcode, teremos q recuperar o pedido pelo numeroPedido e atualizá-lo
        DadosPedido dadosPedido = new DadosPedido(gerarNumeroDoPedido.gerar(dadosCadastroPedido.convertToPedido()));

        // TODO: mensagem solicitando novo pagamento, qrCode será recebido via fila
        String qrCodePagamento = gerarQrCode.gerar(dadosPedido);

        //TODO: Listener para receber o qrCode, assim que receber, atualizad o pedido e enviar atualização para o Produçao
        Pedido pedido = atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido(), qrCodePagamento);

        dadosPedido = new DadosPedido(pedido);

        // TODO: Essa criaçao de pedido será feita antes
        criacaoDePedido.criar(dadosPedido);

        return ResponseEntity.ok().body(dadosPedido);
    }

}
