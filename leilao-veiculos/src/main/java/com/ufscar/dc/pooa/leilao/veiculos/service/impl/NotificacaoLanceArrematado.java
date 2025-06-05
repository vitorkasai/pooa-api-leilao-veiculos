package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Objects;

import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;

public class NotificacaoLanceArrematado implements NotificacaoService {
	private static NotificacaoLanceArrematado notificacaoLanceArrematado;
	
	private NotificacaoLanceArrematado() {
		
	}
	
	public static NotificacaoLanceArrematado getInstancia() {
		if (Objects.isNull(notificacaoLanceArrematado)) {
			return new NotificacaoLanceArrematado();
		}
		return notificacaoLanceArrematado;
	}
	
	@Override
	public void createNotificacao() {
		// TODO Auto-generated method stub
		
	}
	
}