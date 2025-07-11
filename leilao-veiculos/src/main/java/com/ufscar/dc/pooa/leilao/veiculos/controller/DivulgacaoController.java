package com.ufscar.dc.pooa.leilao.veiculos.controller;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.DivulgacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/divulgacao")
public class DivulgacaoController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(DivulgacaoController.class);
    private final DivulgacaoService service;

    @GetMapping("/{uid}")
    public ResponseEntity<ReturnDivulgacaoDTO> findByUid(@PathVariable String uid) {
        log.info("Buscando divulgação de UID: {}", uid);
        return new ResponseEntity<>(service.findByUid(uid), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReturnDivulgacaoDTO>> findAll() {
        log.info("Listando todas as divulgações");
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateDivulgacaoDTO dto) {
        log.info("Criando novo link de divulgação: {}", dto.getNome());
        service.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<Void> delete(@PathVariable String uid) {
        log.info("Deletando divulgação: {}", uid);
        service.delete(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}