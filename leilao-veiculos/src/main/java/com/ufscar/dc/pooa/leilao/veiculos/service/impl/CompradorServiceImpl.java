package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.repository.CompradorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import com.ufscar.dc.pooa.leilao.veiculos.util.ValidatorUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CompradorServiceImpl implements CompradorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final CompradorRepository repository;
    private final CompradorBuilder builder;

    @Override
    public Comprador findDomainById(Long id) {
        log.debug("Buscando comprador: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar comprador de id: " + id));
    }

    @Override
    public void create(CreateCompradorDTO dto) {
        log.debug("Criando novo comprador: {}", dto);
        ValidatorUtil.validate(dto);
        repository.save(builder.build(dto));
    }
}