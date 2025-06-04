package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;

public interface CompradorService {
    Comprador findDomainById(Long id);
    void create(CompradorDTO dto);
}