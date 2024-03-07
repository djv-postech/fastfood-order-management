package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.cliente;

import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.entities.cliente.Cliente;
import com.fiap.postech.techchallenge.fastfoodordermanagement.core.domain.usecases.cliente.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes", description = "Rest api para operações de clientes")
public class ClienteController {

  private final CadastroDeCliente cadastroDeCliente;
  private final ListagemDeCliente listagemDeCliente;
  private final IdentificacaoDeCliente identificacaoDeCliente;
  private final ExclusaoDeCliente exclusaoDeCliente;


  public ClienteController(CadastroDeCliente cadastroDeCliente,
                           ListagemDeCliente listagemDeCliente,
                           IdentificacaoDeCliente identificacaoDeCliente,
                           ExclusaoDeCliente exclusaoDeCliente){
    this.cadastroDeCliente = cadastroDeCliente;
    this.listagemDeCliente = listagemDeCliente;
    this.identificacaoDeCliente = identificacaoDeCliente;
    this.exclusaoDeCliente = exclusaoDeCliente;
  }

  @Operation(summary = "Cadastrar novo cliente")
  @PostMapping
  public ResponseEntity<DadosCliente> cadastrar(
          @Valid @RequestBody DadosCliente dadosCliente) {

    cadastroDeCliente.cadastrar(dadosCliente.convertToCliente());

    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Identificar cliente pelo CPF")
  @GetMapping("/{cpf}")
  public ResponseEntity<DadosCliente> identificarPorCpf(@PathVariable String cpf) {

    Cliente cliente = identificacaoDeCliente.identificarPorCpf(cpf);

    return Objects.nonNull(cliente)
        ? ResponseEntity.ok(new DadosCliente(cliente))
        : ResponseEntity.notFound().build();
  }

  @Operation(summary = "Remover cliente por CPF")
  @DeleteMapping("/{cpf}")
  public ResponseEntity<DadosCliente> excluirPorCpf(@PathVariable String cpf){
    exclusaoDeCliente.excluirPorCpf(cpf);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Listar clientes")
  @GetMapping("/todos")
  public ResponseEntity<List<DadosCliente>> listarClientes(){
    List<Cliente> clientes = listagemDeCliente.todos();
    return Objects.nonNull(clientes) ? ResponseEntity.ok(clientes.stream()
        .map(DadosCliente::new).collect(Collectors.toList())):
        ResponseEntity.notFound().build();
  }
}
