package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Objects;

import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;

public class NotificacaoLanceRecebido implements NotificacaoService {
	private static NotificacaoLanceRecebido notificacaoLanceRecebido;

	private NotificacaoLanceRecebido() {
	
	}

	public static NotificacaoLanceRecebido getInstancia() {
		if (Objects.isNull(notificacaoLanceRecebido)) {
			return new NotificacaoLanceRecebido();
		}
		return notificacaoLanceRecebido;
	}

	@Override
	public void createNotificacao() {
		System.out.println("Lance recebido");
		
	}

}