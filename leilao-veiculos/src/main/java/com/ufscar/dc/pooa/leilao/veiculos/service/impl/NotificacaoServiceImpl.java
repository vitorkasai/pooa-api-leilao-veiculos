package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.NotificacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.NotificacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.repository.NotificacaoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService{
	private static final AppLogger log = AppLoggerFactory.getAppLogger(NotificacaoServiceImpl.class);

	private final NotificacaoRepository repository;
	private final NotificacaoBuilder builder;
	
	@Override
	public List<NotificacaoDTO> listAllByUserId(Long userId) {
		log.debug("Listando todas as notificações não visualizadas do usuário de ID: {}", userId);
		List<NotificacaoDTO> notificacaoDTOList = repository.findAllByUserId(userId).stream().map(builder::build).collect(Collectors.toList());
		return notificacaoDTOList;
	}
}