package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;

import java.util.List;

public interface LanceService {
    List<ReturnLanceDTO> findAllByOfferId(Long id);
    void create(CreateLanceDTO dto);
}
