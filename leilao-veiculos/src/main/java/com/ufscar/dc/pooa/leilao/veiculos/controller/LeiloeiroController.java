package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLeiloeiroDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.LeiloeiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leiloeiro")
public class LeiloeiroController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(LeiloeiroController.class);
    private final LeiloeiroService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateLeiloeiroDTO dto) {
        log.info("Criando um novo leiloeiro");
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
