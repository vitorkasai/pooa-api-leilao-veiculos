package com.ufscar.dc.pooa.leilao.veiculos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufscar.dc.pooa.leilao.veiculos.dto.NotificacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {
	private static final AppLogger log = AppLoggerFactory.getAppLogger(NotificacaoController.class);
	private final NotificacaoService service;
	
	@GetMapping
	public ResponseEntity<List<NotificacaoDTO>> listAllByUserId(@RequestParam Long idUsuario) {
		log.info("Listando todas as notificações para o usuário de ID: {}", idUsuario);
		return new ResponseEntity<>(service.listAllByUserId(idUsuario), HttpStatus.OK);
	}
	
	@PatchMapping("/visualizar")
	public ResponseEntity<Void> visualizarNotificacao(@RequestParam Long idNotificacao) {
		log.info("Visualizando a notificação de ID: {}", idNotificacao);
		service.visualizar(idNotificacao);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
