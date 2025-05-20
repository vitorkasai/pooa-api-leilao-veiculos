package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.VeiculoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VeiculoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final VeiculoRepository repository;
    private final VeiculoBuilder builder;

    public VeiculoServiceImpl(VeiculoRepository repository, VeiculoBuilder builder) {
        this.repository = repository;
        this.builder = builder;
    }

    @Override
    public void create(VeiculoDTO dto) {
        log.debug("Criando novo veículo: {}", dto);
        validate(dto);
        repository.save(builder.build(dto));
    }

    private static void validate(VeiculoDTO dto) {
        Optional.ofNullable(dto.getModelo()).orElseThrow(() -> new BadRequestException("campo modelo é obrigatório"));
        Optional.ofNullable(dto.getPlaca()).orElseThrow(() -> new BadRequestException("campo placa é obrigatório"));
        Optional.ofNullable(dto.getCor()).orElseThrow(() -> new BadRequestException("campo cor é obrigatório"));
        Optional.ofNullable(dto.getRenavam()).orElseThrow(() -> new BadRequestException("campo renavam é obrigatório"));
        Optional.ofNullable(dto.getTipoVeiculo()).orElseThrow(() -> new BadRequestException("campo tipoVeiculo é obrigatório"));

        if ("CARRO".equalsIgnoreCase(dto.getTipoVeiculo())) {
            Optional.ofNullable(dto.getNumeroPortas()).orElseThrow(() -> new BadRequestException("campo numeroPortas é obrigatório para CARRO"));
            Optional.ofNullable(dto.getDirecao()).orElseThrow(() -> new BadRequestException("campo direcao é obrigatório para CARRO"));
            Optional.ofNullable(dto.getCambio()).orElseThrow(() -> new BadRequestException("campo cambio é obrigatório para CARRO"));
        } else if ("MOTO".equalsIgnoreCase(dto.getTipoVeiculo())) {
            Optional.ofNullable(dto.getCilindrada()).orElseThrow(() -> new BadRequestException("campo cilindrada é obrigatório para MOTO"));
            Optional.ofNullable(dto.getMarcha()).orElseThrow(() -> new BadRequestException("campo marcha é obrigatório para MOTO"));
            Optional.ofNullable(dto.getPartida()).orElseThrow(() -> new BadRequestException("campo partida é obrigatório para MOTO"));
        } else {
            throw new BadRequestException("Tipo de veículo inválido: " + dto.getTipoVeiculo());
        }
    }
}
