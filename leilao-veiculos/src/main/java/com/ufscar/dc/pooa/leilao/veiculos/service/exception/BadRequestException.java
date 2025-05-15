package com.ufscar.dc.pooa.leilao.veiculos.service.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
