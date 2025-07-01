package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oferta")
public class OfertaController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(OfertaController.class);
    private final OfertaService service;

    @GetMapping
    public ResponseEntity<List<ReturnOfertaDTO>> findAll() {
        log.info("Listando todas as ofertas");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReturnOfertaDTO>> findAllByEstado(@PathVariable Estado estado) {
        log.info("Listando todas as ofertas com estado: {}", estado);
        return new ResponseEntity<>(service.findAllByEstado(estado), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateOfertaDTO dto) {
        log.info("Criando uma nova oferta");
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CreateOfertaDTO dto) {
        log.info("Atualizando uma oferta");
        service.update(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long id) {
        log.info("Cancelando uma oferta");
        service.updateEstado(id, Estado.CANCELADO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
