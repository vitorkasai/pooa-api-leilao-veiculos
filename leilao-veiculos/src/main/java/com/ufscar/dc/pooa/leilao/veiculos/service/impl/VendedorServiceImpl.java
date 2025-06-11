package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.VendedorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVendedorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VendedorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VendedorServiceImpl implements VendedorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final VendedorRepository repository;;
    private final VendedorBuilder builder;

    @Override
    public Vendedor findDomainById(Long id) {
        log.debug("Buscando vendedor: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar vendedor de id: " + id));
    }
    
    @Override
    public void create(CreateVendedorDTO dto) {
    	log.debug("Criando novo vendedor: {}", dto);
    	validate(dto);
    	repository.save(builder.build(dto));
    }
    
    private static void validate(CreateVendedorDTO dto) {
        Optional.ofNullable(dto.getNome()).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
        Optional.ofNullable(dto.getSobrenome()).orElseThrow(() -> new BadRequestException("Campo sobrenome é obrigatório"));
        Optional.ofNullable(dto.getEmail()).orElseThrow(() -> new BadRequestException("Campo email é obrigatório"));
        Optional.ofNullable(dto.getTelefone()).orElseThrow(() -> new BadRequestException("Campo telefone é obrigatório"));
        Optional.ofNullable(dto.getDocumento()).orElseThrow(() -> new BadRequestException("Campo documento é obrigatório"));
        Optional.ofNullable(dto.getNomeFantasia()).orElseThrow(() -> new BadRequestException("Campo nomeFantasia é obrigatório"));
        Optional.ofNullable(dto.getContaBancaria()).orElseThrow(() -> new BadRequestException("Campo contaBancaria é obrigatório"));
    }
}