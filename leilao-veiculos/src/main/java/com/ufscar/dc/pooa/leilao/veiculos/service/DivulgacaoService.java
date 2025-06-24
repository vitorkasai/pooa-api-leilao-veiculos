package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnDivulgacaoDTO;

import java.util.UUID;

public interface DivulgacaoService {
	ReturnDivulgacaoDTO findByUid(UUID uid);
	void create(CreateDivulgacaoDTO dto);
	void delete(UUID uid);
}
