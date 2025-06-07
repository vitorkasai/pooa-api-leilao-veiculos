package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("lanceArrematado")
@RequiredArgsConstructor
public class NotificacaoLanceArrematado implements CreateNotificacaoService {
	@Override
	public void createNotification(Lance lance) {
		// TODO Auto-generated method stub
	}
}