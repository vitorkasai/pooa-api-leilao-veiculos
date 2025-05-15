package com.ufscar.dc.pooa.leilao.veiculos.controller.exception;

import com.ufscar.dc.pooa.leilao.veiculos.service.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.service.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestStandardError> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestStandardError err = new RestStandardError();
        err.setError("Bad request exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestStandardError> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestStandardError err = new RestStandardError();
        err.setError("Not found exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }
}
