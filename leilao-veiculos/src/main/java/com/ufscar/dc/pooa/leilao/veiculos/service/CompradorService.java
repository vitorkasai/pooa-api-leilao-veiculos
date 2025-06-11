package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;

public interface CompradorService {
    Comprador findDomainById(Long id);
    void create(CreateCompradorDTO dto);
}