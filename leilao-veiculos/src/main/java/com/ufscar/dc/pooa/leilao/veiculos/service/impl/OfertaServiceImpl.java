package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.dto.OfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.TipoVeiculo;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.repository.OfertaRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OfertaServiceImpl implements OfertaService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final OfertaRepository repository;

    @Override
    public void create(OfertaDTO dto) {
        log.debug("Criando nova oferta: {}", dto);
        validate(dto);

    }

    private static void validate(OfertaDTO dto) {
        Optional.ofNullable(dto.getDataInicio()).orElseThrow(() -> new BadRequestException("campo dataInicio é obrigatório"));
        Optional.ofNullable(dto.getDataFim()).orElseThrow(() -> new BadRequestException("campo dataFim é obrigatório"));
        Optional.ofNullable(dto.getValorInicial()).orElseThrow(() -> new BadRequestException("campo valorInicial é obrigatório"));
        Optional.ofNullable(dto.getValorIncremental()).orElseThrow(() -> new BadRequestException("campo valorIncremental é obrigatório"));
        Optional.ofNullable(dto.getEstado()).orElseThrow(() -> new BadRequestException("campo estado é obrigatório"));
        Optional.ofNullable(dto.getIdVendedor()).orElseThrow(() -> new BadRequestException("campo idVendedor é obrigatório"));
        Optional.ofNullable(dto.getIdVeiculo()).orElseThrow(() -> new BadRequestException("campo idVeiculo é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getEstado()).orElseThrow(() -> new BadRequestException("campo endereco.estado é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCidade()).orElseThrow(() -> new BadRequestException("campo endereco.cidade é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getCep()).orElseThrow(() -> new BadRequestException("campo endereco.cep é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getBairro()).orElseThrow(() -> new BadRequestException("campo endereco.bairro é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getRua()).orElseThrow(() -> new BadRequestException("campo endereco.rua é obrigatório"));
        Optional.ofNullable(dto.getEndereco().getNumero()).orElseThrow(() -> new BadRequestException("campo endereco.numero é obrigatório"));
    }
}
