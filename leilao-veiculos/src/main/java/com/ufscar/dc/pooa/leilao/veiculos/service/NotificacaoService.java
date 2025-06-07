package com.ufscar.dc.pooa.leilao.veiculos.service;

import java.util.List;

import com.ufscar.dc.pooa.leilao.veiculos.dto.NotificacaoDTO;

public interface NotificacaoService {
	public List<NotificacaoDTO> listAllByUserId(Long id);
	public void view(Long id);
}
