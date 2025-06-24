package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;

import java.util.UUID;

public interface DivulgacaoService {
	void create(CreateDivulgacaoDTO dto);
	void delete(UUID uid);
}
