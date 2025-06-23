package com.ufscar.dc.pooa.leilao.veiculos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.framework.PersistenciaFramework;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Divulgacao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/divulgacao")
public class DivulgacaoController {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(DivulgacaoController.class);

    @PostMapping
    public ResponseEntity<Void> create() {
        //log.info("Criando novo link de divulgação: {}", dto);
        try {
        	 PersistenciaFramework framework = new PersistenciaFramework();
             
             Divulgacao divulgacao = new Divulgacao("UID_teste", "nome", "link");
             framework.save(divulgacao);
        }
        catch (Exception e) {
        	throw new BadRequestException("error");
        }     
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}