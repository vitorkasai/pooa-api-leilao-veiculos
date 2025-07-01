package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLeiloeiroDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Leiloeiro;

public interface LeiloeiroService {
    Leiloeiro findDomainById(Long id);
    void create(CreateLeiloeiroDTO dto);
}
