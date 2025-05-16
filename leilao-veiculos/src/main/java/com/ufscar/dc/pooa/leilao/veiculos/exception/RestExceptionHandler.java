package com.ufscar.dc.pooa.leilao.veiculos.exception;

import com.ufscar.dc.pooa.leilao.veiculos.dto.RestStandardErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestStandardErrorDTO> handleBadRequestException(BadRequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestStandardErrorDTO err = new RestStandardErrorDTO();
        err.setError("Bad request exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestStandardErrorDTO> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestStandardErrorDTO err = new RestStandardErrorDTO();
        err.setError("Not found exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        return ResponseEntity.status(status).body(err);
    }
}
