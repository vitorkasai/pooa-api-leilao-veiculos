package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnDivulgacaoDTO;

import java.util.List;

public interface DivulgacaoService {
	ReturnDivulgacaoDTO findByUid(String uid);
	List<ReturnDivulgacaoDTO> findAll();
	void create(CreateDivulgacaoDTO dto);
	void delete(String uid);

}
