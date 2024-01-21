package com.fiap.postech.techchallenge.fastfoodordermanagement.application.api.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
public class ApiError {

    private String message;
    private int status;

    public ApiError(String message, int statusCode){
        this.message = message;
        this.status = statusCode;
    }
}
