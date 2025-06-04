package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.LanceBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.LanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.repository.LanceRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LanceServiceImpl implements LanceService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final CompradorService compradorService;
    private final OfertaService ofertaService;
    private final LanceBuilder builder;
    private final LanceRepository repository;

    @Override
    public void create(LanceDTO dto) {
        log.debug("Criando novo lance: {}", dto);
        validate(dto);
        Comprador comprador = compradorService.findDomainById(dto.getIdComprador());
        Oferta oferta = ofertaService.findDomainById(dto.getIdOferta());

        repository.save(builder.build(dto, comprador, oferta));
    }

    private static void validate(LanceDTO dto) {
        Optional.ofNullable(dto.getValor()).orElseThrow(() -> new BadRequestException("campo valor é obrigatório"));
        Optional.ofNullable(dto.getIdComprador()).orElseThrow(() -> new BadRequestException("campo idComprador é obrigatório"));
        Optional.ofNullable(dto.getIdOferta()).orElseThrow(() -> new BadRequestException("campo idOferta é obrigatório"));
    }
}
