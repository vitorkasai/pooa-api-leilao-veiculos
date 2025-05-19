package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.repository.CompradorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CompradorServiceImpl implements CompradorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final CompradorRepository repository;
    private final CompradorBuilder builder;

    public void create(CompradorDTO dto) {
        log.debug("Criando novo comprador: {}", dto);
        validate(dto);
        repository.save(builder.build(dto));
    }

    private static void validate(CompradorDTO dto) {
        Optional.ofNullable(dto.getNome()).orElseThrow(() -> new NotFoundException("campo nome é obrigatório"));
        Optional.ofNullable(dto.getSobrenome()).orElseThrow(() -> new NotFoundException("campo sobrenome é obrigatório"));
        Optional.ofNullable(dto.getEmail()).orElseThrow(() -> new NotFoundException("campo email é obrigatório"));
        Optional.ofNullable(dto.getTelefone()).orElseThrow(() -> new NotFoundException("campo telefone é obrigatório"));
        Optional.ofNullable(dto.getDocumento()).orElseThrow(() -> new NotFoundException("campo documento é obrigatório"));
        Optional.ofNullable(dto.getInteresse()).orElseThrow(() -> new NotFoundException("campo interesse é obrigatório"));
        Optional.ofNullable(dto.getDataNascimento()).orElseThrow(() -> new NotFoundException("campo dataNascimento é obrigatório"));
    }
}