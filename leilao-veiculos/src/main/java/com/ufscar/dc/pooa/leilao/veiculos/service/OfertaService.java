package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;

import java.util.List;

public interface OfertaService {
    Oferta findDomainById(Long id);
    List<ReturnOfertaDTO> findAll();
    List<Oferta> findAllDomain();
    List<ReturnOfertaDTO> findAllByEstado(Estado estado);
    void create(CreateOfertaDTO dto);
    void update(Long id, CreateOfertaDTO dto);
    void updateEstado(Long id, Estado estado);
}
