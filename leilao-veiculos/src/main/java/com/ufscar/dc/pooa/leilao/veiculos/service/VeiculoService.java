package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;

public interface VeiculoService {
    Veiculo findDomainById(Long id);
    Long create(CreateVeiculoDTO dto);
}