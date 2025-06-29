package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lance")
public class LanceController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(LanceController.class);
    private final LanceService service;

    @GetMapping("/oferta/{ofertaId}/vencedor")
    public ResponseEntity<ReturnLanceDTO> findVencedorByOfertaId(@PathVariable Long ofertaId) {
        log.info("Buscando lance vencedor com oferta de ID: {}", ofertaId);
        return new ResponseEntity<>(service.findVencedorByOfertaId(ofertaId), HttpStatus.OK);
    }

    @GetMapping("/oferta/{ofertaId}")
    public ResponseEntity<List<ReturnLanceDTO>> findAllByOfertaId(@PathVariable Long ofertaId) {
        log.info("Listando todos os lances com oferta de ID: {}", ofertaId);
        return new ResponseEntity<>(service.findAllByOfertaId(ofertaId), HttpStatus.OK);
    }

    @GetMapping("/comprador/{compradorId}")
    public ResponseEntity<List<ReturnLanceDTO>> findAllByCompradorId(@PathVariable Long compradorId) {
        log.info("Listando todos os lances com comprador de ID: {}", compradorId);
        return new ResponseEntity<>(service.findAllByCompradorId(compradorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateLanceDTO dto) {
        log.info("Criando novo lance: {}", dto);
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
