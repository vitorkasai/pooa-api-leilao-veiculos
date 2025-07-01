package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;

import java.util.List;
import java.util.Optional;

public interface LanceService {
    ReturnLanceDTO findVencedorByOfertaId(Long id);
    Optional<Lance> findVencedorOptDomainByOfertaId(Long ofertaId);
    List<ReturnLanceDTO> findAllByOfertaId(Long id);
    List<ReturnLanceDTO> findAllByCompradorId(Long id);
    void create(CreateLanceDTO dto);
}
