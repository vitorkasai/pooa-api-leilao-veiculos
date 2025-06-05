package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Objects;

import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;

public class NotificacaoLanceSuperado implements NotificacaoService {
	private static NotificacaoLanceSuperado notificacaoLanceSuperado;
	
	private NotificacaoLanceSuperado() {	
	
	}
	
	public static NotificacaoLanceSuperado getInstancia() {
		if (Objects.isNull(notificacaoLanceSuperado)) {
			return new NotificacaoLanceSuperado();
		}
		return notificacaoLanceSuperado;
	}
	
	@Override
	public void createNotificacao() {
		// TODO Auto-generated method stub
		
	}

}
