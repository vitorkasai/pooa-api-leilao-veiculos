package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
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

    @Override
    public Comprador findDomainById(Long id) {
        log.debug("Buscando comprador: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar comprador de id: " + id));
    }

    @Override
    public void create(CreateCompradorDTO dto) {
        log.debug("Criando novo comprador: {}", dto);
        validate(dto);
        repository.save(builder.build(dto));
    }

    private static void validate(CreateCompradorDTO dto) {
        Optional.ofNullable(dto.getNome()).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
        Optional.ofNullable(dto.getSobrenome()).orElseThrow(() -> new BadRequestException("Campo sobrenome é obrigatório"));
        Optional.ofNullable(dto.getEmail()).orElseThrow(() -> new BadRequestException("Campo email é obrigatório"));
        Optional.ofNullable(dto.getTelefone()).orElseThrow(() -> new BadRequestException("Campo telefone é obrigatório"));
        Optional.ofNullable(dto.getDocumento()).orElseThrow(() -> new BadRequestException("Campo documento é obrigatório"));
        Optional.ofNullable(dto.getInteresse()).orElseThrow(() -> new BadRequestException("Campo interesse é obrigatório"));
        Optional.ofNullable(dto.getDataNascimento()).orElseThrow(() -> new BadRequestException("Campo dataNascimento é obrigatório"));
    }
}