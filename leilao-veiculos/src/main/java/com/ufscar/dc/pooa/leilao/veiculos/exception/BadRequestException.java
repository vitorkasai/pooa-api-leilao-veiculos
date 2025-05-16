package com.ufscar.dc.pooa.leilao.veiculos.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
