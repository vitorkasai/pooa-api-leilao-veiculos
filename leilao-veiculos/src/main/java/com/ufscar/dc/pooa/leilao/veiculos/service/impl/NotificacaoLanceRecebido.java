package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;
import lombok.NoArgsConstructor;

import java.util.Objects;

public class NotificacaoLanceRecebido implements NotificacaoService {
	private static NotificacaoLanceRecebido notificacaoLanceRecebido;

	private NotificacaoLanceRecebido() {
	}

	public static NotificacaoLanceRecebido getInstancia() {
		if (Objects.isNull(notificacaoLanceRecebido)) {
			notificacaoLanceRecebido = new NotificacaoLanceRecebido();
		}
		return notificacaoLanceRecebido;
	}

	@Override
	public void createNotificacao() {
		System.out.println("Lance recebido");
		
	}

}
