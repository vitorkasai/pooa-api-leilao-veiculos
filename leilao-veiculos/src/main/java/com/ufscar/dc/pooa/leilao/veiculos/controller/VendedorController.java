package com.ufscar.dc.pooa.leilao.veiculos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/vendedor")
public class VendedorController {
	private static final AppLogger log = AppLoggerFactory.getAppLogger(VendedorController.class);
	private final VendedorService service;
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody CreateVendedorDTO dto) {
		log.info("Criando um novo vendedor");
		service.create(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}