package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;

public interface OfertaService {
    Oferta findDomainById(Long id);
    void create(CreateOfertaDTO dto);
}
