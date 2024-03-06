package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.RegistroDeCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.estoque.SubtracaoDeEstoqueMessageService;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.pagamento.SolicitacaoDePagamentoMessageService;
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

    private final SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService;

    private final SolicitacaoDePagamentoMessageService solicitacaoDePagamentoMessageService;

    private final GerarNumeroDoPedido gerarNumeroDoPedido;

    public PedidoController(RegistroDeCliente registroDeCliente, SubtracaoDeEstoqueMessageService subtracaoDeEstoqueMessageService, SolicitacaoDePagamentoMessageService solicitacaoDePagamentoMessageService, GerarNumeroDoPedido gerarNumeroDoPedido) {
        this.registroDeCliente = registroDeCliente;
       this.subtracaoDeEstoqueMessageService = subtracaoDeEstoqueMessageService;
        this.solicitacaoDePagamentoMessageService = solicitacaoDePagamentoMessageService;
        this.gerarNumeroDoPedido = gerarNumeroDoPedido;
    }

    @Operation(summary = "Checkout de Pedidos")
    @PostMapping
    public ResponseEntity<DadosPedido> cadastrarPedido(
            @Valid @RequestBody DadosCadastroPedido dadosCadastroPedido) {

        if (Objects.nonNull(dadosCadastroPedido.cliente())) {
            registroDeCliente.registrar(dadosCadastroPedido.cliente().convertToCliente());
        }

        subtracaoDeEstoqueMessageService.subtrairEstoque(dadosCadastroPedido.convertToPedido().getProdutos());

        DadosPedido dadosPedido = new DadosPedido(gerarNumeroDoPedido.gerar(dadosCadastroPedido.convertToPedido()));

        solicitacaoDePagamentoMessageService.solicitarPagamento(dadosPedido.convertToPedido());

        return ResponseEntity.ok().body(dadosPedido);
    }

}
