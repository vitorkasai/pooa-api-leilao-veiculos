package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.NotificacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.repository.NotificacaoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("lanceSuperado")
@RequiredArgsConstructor
public class NotificacaoLanceSuperado implements CreateNotificacaoService {
	private final NotificacaoRepository repository;
	private final NotificacaoBuilder builder;
	
	@Override
	public void createNotification(Lance lance) {
		// TODO Auto-generated method stub
	}
}