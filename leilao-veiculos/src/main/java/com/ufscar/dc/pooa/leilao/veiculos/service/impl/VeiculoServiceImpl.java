package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.factory.VeiculoFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.TipoVeiculo;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VeiculoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VeiculoServiceImpl implements VeiculoService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final VeiculoRepository repository;

    @Override
    public void create(VeiculoDTO dto) {
        log.debug("Criando novo veículo: {}", dto);
        validate(dto);

        String tipo = dto.getTipoVeiculo();
        Veiculo veiculo;

        if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
            veiculo = VeiculoFactory.criarCarro(dto);
        } else if (TipoVeiculo.MOTO.name().equalsIgnoreCase(tipo)) {
            veiculo = VeiculoFactory.criarMoto(dto);
        } else {
            throw new BadRequestException("Tipo de veículo inválido: " + tipo);
        }

        repository.save(veiculo);
    }

    private static void validate(VeiculoDTO dto) {
        Optional.ofNullable(dto.getModelo()).orElseThrow(() -> new BadRequestException("campo modelo é obrigatório"));
        Optional.ofNullable(dto.getPlaca()).orElseThrow(() -> new BadRequestException("campo placa é obrigatório"));
        Optional.ofNullable(dto.getCor()).orElseThrow(() -> new BadRequestException("campo cor é obrigatório"));
        Optional.ofNullable(dto.getRenavam()).orElseThrow(() -> new BadRequestException("campo renavam é obrigatório"));
        Optional.ofNullable(dto.getTipoVeiculo()).orElseThrow(() -> new BadRequestException("campo tipoVeiculo é obrigatório"));

        String tipo = dto.getTipoVeiculo();

        if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
            Optional.ofNullable(dto.getNumeroPortas()).orElseThrow(() -> new BadRequestException("campo numeroPortas é obrigatório para CARRO"));
            Optional.ofNullable(dto.getDirecao()).orElseThrow(() -> new BadRequestException("campo direcao é obrigatório para CARRO"));
            Optional.ofNullable(dto.getCambio()).orElseThrow(() -> new BadRequestException("campo cambio é obrigatório para CARRO"));
        } else if (TipoVeiculo.MOTO.name().equalsIgnoreCase(tipo)) {
            Optional.ofNullable(dto.getCilindrada()).orElseThrow(() -> new BadRequestException("campo cilindrada é obrigatório para MOTO"));
            Optional.ofNullable(dto.getMarcha()).orElseThrow(() -> new BadRequestException("campo marcha é obrigatório para MOTO"));
            Optional.ofNullable(dto.getPartida()).orElseThrow(() -> new BadRequestException("campo partida é obrigatório para MOTO"));
        } else {
            throw new BadRequestException("Tipo de veículo inválido: " + tipo);
        }
    }
}
