package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.repository.CompradorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompradorService {
    private final CompradorRepository repository;
    private final CompradorBuilder builder;

    public void create(CompradorDTO dto) {
        log.debug("Criando novo comprador: {}", dto);
        repository.save(builder.build(dto));
    }
}
