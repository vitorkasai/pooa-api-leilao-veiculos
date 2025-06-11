package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.NotificacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.repository.NotificacaoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;

import lombok.RequiredArgsConstructor;

@Service("lanceRecebido")
@RequiredArgsConstructor
public class NotificacaoLanceRecebido implements CreateNotificacaoService {
	private final NotificacaoRepository repository;
	private final NotificacaoBuilder builder;

	@Override
	public void createNotificacao(Lance lance) {
		String conteudo = "Sua oferta recebeu um novo lance no valor de: " + lance.getValor() + " R$";
		repository.save(builder.build(conteudo, lance.getOferta().getVendedor()));
	}
}