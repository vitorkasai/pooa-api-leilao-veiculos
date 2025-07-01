package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    private static final AppLogger log =  AppLoggerFactory.getAppLogger(VeiculoController.class);
    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateVeiculoDTO dto) {
        log.info("Criando um novo veiculo: {}");
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
