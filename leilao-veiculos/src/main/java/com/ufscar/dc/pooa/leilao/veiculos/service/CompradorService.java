package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import org.springframework.stereotype.Service;

@Service
public interface CompradorService {
    void create(CompradorDTO dto);
}