package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CarroBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.MotoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
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
    private final CarroBuilder carroBuilder;
    private final MotoBuilder motoBuilder;

    @Override
    public Veiculo findDomainById(Long id) {
        log.debug("Buscando veiculo: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar veiculo de id: " + id));
    }

    @Override
    public void create(VeiculoDTO dto) {
        log.debug("Criando novo veículo: {}", dto);
        validate(dto);

        String tipo = dto.getTipoVeiculo();
        Veiculo veiculo;

        if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
            veiculo = carroBuilder.build(dto);
        } else {
            veiculo = motoBuilder.build(dto);
        }

        repository.save(veiculo);
    }

    private static void validate(VeiculoDTO dto) {
        Optional.ofNullable(dto.getModelo()).orElseThrow(() -> new BadRequestException("Campo modelo é obrigatório"));
        Optional.ofNullable(dto.getPlaca()).orElseThrow(() -> new BadRequestException("Campo placa é obrigatório"));
        Optional.ofNullable(dto.getCor()).orElseThrow(() -> new BadRequestException("Campo cor é obrigatório"));
        Optional.ofNullable(dto.getRenavam()).orElseThrow(() -> new BadRequestException("Campo renavam é obrigatório"));
        Optional.ofNullable(dto.getTipoVeiculo()).orElseThrow(() -> new BadRequestException("Campo tipoVeiculo é obrigatório"));

        String tipo = dto.getTipoVeiculo();

        if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
            Optional.ofNullable(dto.getNumeroPortas()).orElseThrow(() -> new BadRequestException("Campo numeroPortas é obrigatório para CARRO"));
            Optional.ofNullable(dto.getDirecao()).orElseThrow(() -> new BadRequestException("Campo direcao é obrigatório para CARRO"));
            Optional.ofNullable(dto.getCambio()).orElseThrow(() -> new BadRequestException("Campo cambio é obrigatório para CARRO"));
        } else if (TipoVeiculo.MOTO.name().equalsIgnoreCase(tipo)) {
            Optional.ofNullable(dto.getCilindrada()).orElseThrow(() -> new BadRequestException("Campo cilindrada é obrigatório para MOTO"));
            Optional.ofNullable(dto.getMarcha()).orElseThrow(() -> new BadRequestException("Campo marcha é obrigatório para MOTO"));
            Optional.ofNullable(dto.getPartida()).orElseThrow(() -> new BadRequestException("Campo partida é obrigatório para MOTO"));
        } else {
            throw new BadRequestException("Tipo de veículo inválido: " + tipo);
        }
    }
}
