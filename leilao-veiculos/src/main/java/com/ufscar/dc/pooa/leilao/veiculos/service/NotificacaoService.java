package com.ufscar.dc.pooa.leilao.veiculos.service;

import java.util.List;

import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnNotificacaoDTO;

public interface NotificacaoService {
	List<ReturnNotificacaoDTO> findAllByUsuarioId(Long id);
	void view(Long id);
}
