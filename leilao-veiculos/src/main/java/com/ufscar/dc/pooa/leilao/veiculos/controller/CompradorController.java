package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comprador")
public class CompradorController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorController.class);
    private final CompradorService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCompradorDTO dto) {
        log.info("Criando novo comprador: {}", dto);
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}