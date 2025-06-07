package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.LanceDTO;

import java.util.List;

public interface LanceService {
    List<LanceDTO> findAllByOfferId(Long id);
    void create(LanceDTO dto);
}
