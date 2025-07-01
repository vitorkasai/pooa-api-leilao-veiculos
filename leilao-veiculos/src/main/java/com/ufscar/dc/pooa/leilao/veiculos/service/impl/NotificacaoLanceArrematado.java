package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.NotificacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.repository.NotificacaoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;

import lombok.RequiredArgsConstructor;

@Service("lanceArrematado")
@RequiredArgsConstructor
public class NotificacaoLanceArrematado implements CreateNotificacaoService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(NotificacaoLanceArrematado.class);
	private final NotificacaoRepository repository;
	private final NotificacaoBuilder builder;
	
	@Override
	public void createNotificacao(Lance lance) {
		String conteudo = "Seu lance no valor de "
				.concat(lance.getValor().toString())
				.concat(" R$ foi arrematado para a oferta do "
				.concat(lance.getOferta().getVeiculo().getModelo()));			
		repository.save(builder.build(conteudo, lance.getComprador()));
		log.debug("Nova notificação de lance arrematado salva: {}", conteudo);
	}
}