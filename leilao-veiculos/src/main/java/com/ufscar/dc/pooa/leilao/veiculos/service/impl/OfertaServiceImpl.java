package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ufscar.dc.pooa.leilao.veiculos.util.CalculatorUtil;
import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.EnderecoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.builder.OfertaBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
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
import com.ufscar.dc.pooa.leilao.veiculos.util.ValidatorUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OfertaServiceImpl implements OfertaService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final VendedorService vendedorService;
    private final VeiculoService veiculoService;
    private final EnderecoBuilder enderecoBuilder;
    private final OfertaBuilder builder;
    private final OfertaRepository repository;
    private final OfertaService ofertaService;

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
        log.debug("Listando todas as ofertas de estado: {}", estado);
        return repository.findAllByEstado(estado).stream().map(builder::build).collect(Collectors.toList());
    }

    @Override
    public void create(CreateOfertaDTO dto) {
        log.debug("Criando nova oferta: {}", dto);
        ValidatorUtil.validate(dto);

        Vendedor vendedor = vendedorService.findDomainById(dto.getVendedorId());

        Long veiculoId = dto.getVeiculoId();
        if (dto.getVeiculo() != null) {
            veiculoId = veiculoService.create(dto.getVeiculo());
        }
        Veiculo veiculo = veiculoService.findDomainById(veiculoId);
        validateDatas(dto.getDhInicio(), dto.getDhFim());

        dto.setEstado(Estado.NAO_INICIADO);
        repository.save(builder.build(dto, vendedor, veiculo, enderecoBuilder.build(dto.getEndereco())));
    }

    @Override
    public void update(Long id, CreateOfertaDTO dto) {
        log.debug("Atualizando oferta {}: {}", id, dto);
        ValidatorUtil.validate(id, dto);

        Oferta oferta = findDomainById(id);

        if (dto.getDhInicio() != null) {
            oferta.setDhInicio(dto.getDhInicio());
        }

        if (dto.getDhFim() != null) {
            oferta.setDhFim(dto.getDhFim());
        }

        validateDatasAtualizacao(oferta.getDhInicio(), oferta.getDhFim());

        if (dto.getVendedorId() != null) {
            Vendedor vendedor = vendedorService.findDomainById(dto.getVendedorId());
            oferta.setVendedor(vendedor);
        }

        if (dto.getVeiculoId() != null || dto.getVeiculo() != null) {
            Long veiculoId = dto.getVeiculo() != null ? veiculoService.create(dto.getVeiculo()) : dto.getVeiculoId();
            Veiculo veiculo = veiculoService.findDomainById(veiculoId);
            oferta.setVeiculo(veiculo);
        }

        if (dto.getEndereco() != null) {
            oferta.setEndereco(enderecoBuilder.build(dto.getEndereco()));
        }

        if (dto.getValorInicial() != null) {
            oferta.setValorInicial(dto.getValorInicial());
        }

        if (dto.getValorIncremental() != null) {
            oferta.setValorIncremental(dto.getValorIncremental());
        }

        oferta.setEstado(CalculatorUtil.calculateEstado(oferta));
        repository.save(oferta);
    }

    @Override
    public void cancel(Long id) {
        log.debug("Cancelando oferta de ID: {}", id);
        Oferta oferta = findDomainById(id);
        validateCancelarOferta(oferta);

        oferta.setEstado(Estado.CANCELADO);
        repository.save(oferta);
    }
    
    private static void validateDatas(LocalDateTime dhInicio, LocalDateTime dhFim) {
        LocalDateTime now = LocalDateTime.now();
        if (dhInicio.isBefore(now)) {
            log.error("O dhInicio {} deve ser mais velha que data atual {}", dhInicio, now);
            throw new BadRequestException("O dhInicio deve ser mais velha que data atual");
        }
        if (dhInicio.isAfter(dhFim)) {
            log.error("O dhFim {} deve ser mais velha que dhInicio {}", dhFim, dhInicio);
            throw new BadRequestException("O dhFim deve ser mais velha que dhInicio");
        }
    }

    private static void validateDatasAtualizacao(LocalDateTime dhInicio, LocalDateTime dhFim) {
        if (dhInicio.isAfter(dhFim)) {
            log.error("O dhFim {} deve ser mais velha que dhInicio {}", dhFim, dhInicio);
            throw new BadRequestException("O dhFim deve ser mais velha que dhInicio");
        }
    }

    private static void validateCancelarOferta(Oferta oferta) {
        if (oferta.getEstado() == Estado.FINALIZADO) {
            log.error("Uma oferta finalizada não pode ser cancelada. Oferta ID: {}", oferta.getId());
            throw new BadRequestException("Uma oferta finalizada não pode ser cancelada");
        }
    }
}
