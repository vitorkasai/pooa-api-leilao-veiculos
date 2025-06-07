package com.ufscar.dc.pooa.leilao.veiculos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<NotificacaoDTO>> listAllByUserId(@PathVariable Long usuarioId) {
		log.info("Listando todas as notificações para o usuário de ID: {}", usuarioId);
		return new ResponseEntity<>(service.listAllByUserId(usuarioId), HttpStatus.OK);
	}
	
	@PatchMapping("/visualizar/{notificacaoId}")
	public ResponseEntity<Void> viewNotification(@PathVariable Long notificacaoId) {
		log.info("Visualizando a notificação de ID: {}", notificacaoId);
		service.view(notificacaoId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
