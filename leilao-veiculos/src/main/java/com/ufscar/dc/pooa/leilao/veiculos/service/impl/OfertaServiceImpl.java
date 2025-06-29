package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.EnderecoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.OfertaBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import com.ufscar.dc.pooa.leilao.veiculos.repository.OfertaRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VeiculoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.VendedorService;
import com.ufscar.dc.pooa.leilao.veiculos.util.Validators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OfertaServiceImpl implements OfertaService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final VendedorService vendedorService;
    private final VeiculoService veiculoService;
    private final EnderecoBuilder enderecoBuilder;
    private final OfertaBuilder builder;
    private final OfertaRepository repository;

    @Override
    public Oferta findDomainById(Long id) {
        log.debug("Buscando oferta: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar oferta de id: " + id));
    }

    @Override
    public List<ReturnOfertaDTO> findAll() {
        log.debug("Listando todas as ofertas");
        return repository.findAll().stream().map(builder::build).collect(Collectors.toList());
    }

    @Override
    public List<ReturnOfertaDTO> findAllByEstado(Estado estado) {
        log.debug("Listando todas as ofertas com estado: {}", estado);
        return repository.findAllByEstado(estado).stream().map(builder::build).collect(Collectors.toList());
    }

    @Override
    public void create(CreateOfertaDTO dto) {
        log.debug("Criando nova oferta: {}", dto);
        Validators.validate(dto);

        Vendedor vendedor = vendedorService.findDomainById(dto.getVendedorId());

        Long veiculoId = dto.getVeiculoId();
        if (dto.getVeiculo() != null) {
            veiculoId = veiculoService.create(dto.getVeiculo());
        }
        Veiculo veiculo = veiculoService.findDomainById(veiculoId);

        Validators.validateDatas(dto, log);

        dto.setEstado(Estado.NAO_INICIADO);
        repository.save(builder.build(dto, vendedor, veiculo, enderecoBuilder.build(dto.getEndereco())));
    }
}
