package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.LanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lance")
public class LanceController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(LanceController.class);
    private final LanceService service;

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody LanceDTO dto) {
        log.info("Criando novo lance: {}", dto);
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
