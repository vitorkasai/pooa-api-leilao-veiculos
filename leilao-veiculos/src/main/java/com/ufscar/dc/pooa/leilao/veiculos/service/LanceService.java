package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;

import java.util.List;

public interface LanceService {
    ReturnLanceDTO findVencedorByOfertaId(Long id);
    List<ReturnLanceDTO> findAllByOfertaId(Long id);
    List<ReturnLanceDTO> findAllByCompradorId(Long id);
    Lance findUltimoLance(Long ofertaId);
    void create(CreateLanceDTO dto);
}
