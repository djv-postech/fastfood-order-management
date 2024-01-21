package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.config;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.estoque.exception.SubtracaoDeEstoqueException;
import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.gateway.feign.pagamento.exception.QrCodePagamentoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ControllerExceptionHandlerTest {
    private static final String EXCEPTION_MESSAGE = "Exception Message";

    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    public void init() {
        controllerExceptionHandler = new ControllerExceptionHandler();
        MockMvcBuilders.standaloneSetup(controllerExceptionHandler).build();
    }

    @Test
    @DisplayName("Test - Deve tratar SubtracaoDeEstoqueException")
    public void trataSubtracaoDeEstoqueException() {
        // Dado
        final var exception = new SubtracaoDeEstoqueException(EXCEPTION_MESSAGE);

        // Quando
        final var response = controllerExceptionHandler.handlerSubtracaoDeEstoqueException(exception);

        // Entao
        final var errorMap = response.getBody();
        assertEquals(response.getStatusCode(), (HttpStatus.BAD_REQUEST));
        assert errorMap != null;
        assertEquals(400, errorMap.getStatus());
    }

    @Test
    @DisplayName("Test - Deve tratar IllegalArgumentException")
    public void trataIllegalArgumentException() {
        // Dado
        final var exception = new IllegalArgumentException(EXCEPTION_MESSAGE);

        // Quando
        final var response = controllerExceptionHandler.handlerIllegalArgumentException(exception);

        // Entao
        final var errorMap = response.getBody();
        assertEquals(response.getStatusCode(), (HttpStatus.BAD_REQUEST));
        assert errorMap != null;
        assertEquals(400, errorMap.getStatus());
    }

    @Test
    @DisplayName("Test - Deve tratar QrCodePagamentoException")
    public void trataQrCodePagamentoException() {
        // Dado
        final var exception = new QrCodePagamentoException(EXCEPTION_MESSAGE);

        // Quando
        final var response = controllerExceptionHandler.handlerQrCodePagamentoException(exception);

        // Entao
        final var errorMap = response.getBody();
        assertEquals(response.getStatusCode(), (HttpStatus.BAD_REQUEST));
        assert errorMap != null;
        assertEquals(400, errorMap.getStatus());
    }
}