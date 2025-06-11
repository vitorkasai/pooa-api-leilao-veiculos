package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;

public interface VendedorService {
    Vendedor findDomainById(Long id);
    void create(CreateVendedorDTO dto);
}
