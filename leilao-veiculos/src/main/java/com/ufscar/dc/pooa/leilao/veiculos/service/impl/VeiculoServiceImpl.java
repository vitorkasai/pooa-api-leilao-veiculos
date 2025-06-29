package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CarroBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.MotoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateVeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.TipoVeiculo;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.repository.VeiculoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import com.ufscar.dc.pooa.leilao.veiculos.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Long create(CreateVeiculoDTO dto) {
        log.debug("Criando novo veículo: {}", dto);
        ValidatorUtil.validate(dto);

        String tipo = dto.getTipoVeiculo();
        Veiculo veiculo;

        if (TipoVeiculo.CARRO.name().equalsIgnoreCase(tipo)) {
            veiculo = carroBuilder.build(dto);
        } else {
            veiculo = motoBuilder.build(dto);
        }

        veiculo = repository.save(veiculo);

        return veiculo.getId();
    }
}
