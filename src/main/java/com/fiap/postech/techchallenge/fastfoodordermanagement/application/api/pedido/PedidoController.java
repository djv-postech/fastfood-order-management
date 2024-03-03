package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.pedido.Pedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoqueMessageService;
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

import java.util.Objects;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos", description = "Rest api para operações de pedidos")
public class PedidoController {

    private final RegistroDeCliente registroDeCliente;

    private final CriacaoDePedido criacaoDePedido;

    private final SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService;

    private final AtualizacaoDePedido atualizacaoDePedido;

    private final GerarQrCode gerarQrCode;

    private final GerarNumeroDoPedido gerarNumeroDoPedido;

    public PedidoController(RegistroDeCliente registroDeCliente, CriacaoDePedido criacaoDePedido, SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService, AtualizacaoDePedido atualizacaoDePedido, GerarQrCode gerarQrCode, GerarNumeroDoPedido gerarNumeroDoPedido) {
        this.registroDeCliente = registroDeCliente;
        this.criacaoDePedido = criacaoDePedido;
        this.subtracaoDeEstoqueMessageService = subtracaoDeEstoqueMessageService;
        this.atualizacaoDePedido = atualizacaoDePedido;
        this.gerarQrCode = gerarQrCode;
        this.gerarNumeroDoPedido = gerarNumeroDoPedido;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido) {

        if(Objects.nonNull(dadosCadastroPedido.cliente())) {
            registroDeCliente.registrar(dadosCadastroPedido.cliente().convertToCliente());
        }

        subtracaoDeEstoqueMessageService.subtrairEstoque(dadosCadastroPedido.convertToPedido().getProdutos());

        DadosPedido dadosPedido = new DadosPedido(gerarNumeroDoPedido.gerar(dadosCadastroPedido.convertToPedido()));

        //String qrCodePagamento = gerarQrCode.gerar(dadosPedido);

        //Pedido pedido = atualizacaoDePedido.atualizarPedido(dadosPedido.convertToPedido(), qrCodePagamento);

        //dadosPedido = new DadosPedido(pedido);

        criacaoDePedido.criar(dadosPedido);

        return ResponseEntity.ok().body(dadosPedido);
    }

}
