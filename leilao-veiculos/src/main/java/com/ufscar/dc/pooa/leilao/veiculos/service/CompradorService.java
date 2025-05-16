package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.repository.CompradorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompradorService {
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
