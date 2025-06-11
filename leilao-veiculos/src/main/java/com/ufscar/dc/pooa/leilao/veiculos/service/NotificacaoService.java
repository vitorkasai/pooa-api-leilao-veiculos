package com.ufscar.dc.pooa.leilao.veiculos.service;

import java.util.List;

import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnNotificacaoDTO;

public interface NotificacaoService {
	public List<ReturnNotificacaoDTO> listAllByUserId(Long id);
	public void view(Long id);
}
