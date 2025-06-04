package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.LanceBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.LanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
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
        if (oferta.getEstado() != Estado.EM_ANDAMENTO) {
            log.error("Falha ao validar oferta com estado {} ao realizar lance", oferta.getEstado());
            throw new BadRequestException("Falha ao validar oferta ao realizar lance");
        }
        Optional<Lance> ultimoLanceOpt = repository.findFirstByOfertaIdOrderByDhCriacaoDesc(oferta.getId());
        if (ultimoLanceOpt.isPresent()) {
            Double valorUltimoLance = ultimoLanceOpt.get().getValor();
            if (valorUltimoLance > dto.getValor()) {
                log.error("O valor deve ser maior que o valor do último lance feito: " + oferta.getEstado());
                throw new BadRequestException("O valor deve ser maior que o valor do último lance feito");
            }
            if ((dto.getValor() - valorUltimoLance) < oferta.getValorIncremental()) {
                log.error("Diferença entre o valor e o valor do último lance deve ser maior que o valorIncremental: " + oferta.getValorIncremental());
                throw new BadRequestException("Diferença entre o valor e o valor do último lance deve ser maior que o valorIncremental");
            }
        }
        if (dto.getValor() < oferta.getValorInicial()) {
            log.error("O valor deve ser maior que o valor inicial da oferta: " + oferta.getValorInicial());
            throw new BadRequestException("O valor deve ser maior que o valor inicial da oferta");
        }
        repository.save(builder.build(dto, comprador, oferta));
    }

    private static void validate(LanceDTO dto) {
        Optional.ofNullable(dto.getValor()).orElseThrow(() -> new BadRequestException("Campo valor é obrigatório"));
        Optional.ofNullable(dto.getIdComprador()).orElseThrow(() -> new BadRequestException("Campo idComprador é obrigatório"));
        Optional.ofNullable(dto.getIdOferta()).orElseThrow(() -> new BadRequestException("Campo idOferta é obrigatório"));
    }
}
