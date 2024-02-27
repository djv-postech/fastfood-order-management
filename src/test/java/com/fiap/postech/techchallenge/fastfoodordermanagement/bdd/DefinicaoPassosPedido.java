package com.fiap.postech.techchallenge.fastfoodordermanagement.bdd;

import com.fiap.postech.techchallenge.fastfoodordermanagement.PedidoHelper;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.config.ApiError;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosCadastroPedido;
import com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.pedido.records.DadosPedido;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DefinicaoPassosPedido {

    private Response response;
    private final String BASE_URL = "http://localhost:8080";
    private final String ENDPOINT_API_PEDIDO = BASE_URL + "/pedido";

    @Quando("cadastrar um novo pedido")
    public DadosPedido pedido() {

        DadosCadastroPedido dadosCadastroPedido = PedidoHelper.gerarDadosCadastroPedido();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosCadastroPedido)
                .when()
                .post(ENDPOINT_API_PEDIDO);

        return response.then().extract().as(DadosPedido.class);
    }

    @Entao("o pedido é criado e enviado com sucesso")
    public void o_pedido_é_cadastrado_com_sucesso() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Entao("deve ser apresentado")
    public void deve_ser_apresentado() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/dados_pedido.schema.json"));
    }


    @Quando("receber um pedido com dados de cliente inválidos")
    public ApiError receber_pedido_com_dados_de_cliente_invalido() {

        DadosCadastroPedido dadosCadastroPedidoDadosInvalidos = PedidoHelper.gerarDadosCadastroPedidoComEmailClienteNulo();

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(dadosCadastroPedidoDadosInvalidos)
                .when()
                .post(ENDPOINT_API_PEDIDO);

        return response.then().extract().as(ApiError.class);
    }

    @Entao("uma mensagem de erro deve ser apresentada")
    public void uma_mensagem_de_erro_deve_ser_apresentada() {
        response.then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(matchesJsonSchemaInClasspath("schemas/erro.schema.json"));
    }


}
