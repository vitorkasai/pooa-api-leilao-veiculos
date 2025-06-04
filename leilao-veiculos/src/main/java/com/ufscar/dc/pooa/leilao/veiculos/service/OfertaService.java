package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.OfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;

public interface OfertaService {
    Oferta findDomainById(Long id);
    void create(OfertaDTO dto);
}
