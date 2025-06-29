package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.LanceBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.repository.LanceRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
import com.ufscar.dc.pooa.leilao.veiculos.util.Validators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LanceServiceImpl implements LanceService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(VeiculoServiceImpl.class);
    private final Map<String, CreateNotificacaoService> notificacaoStrategy;
    private final CompradorService compradorService;
    private final OfertaService ofertaService;
    private final LanceBuilder builder;
    private final LanceRepository repository;

    @Override
    public List<ReturnLanceDTO> findAllByOfertaId(Long id) {
        log.debug("Listando todas os lances da oferta de ID: {}", id);
        return repository.findAllByOfertaIdOrderByValorDesc(id).stream().map(builder::build).collect(Collectors.toList());
    }

    @Override
    public List<ReturnLanceDTO> findAllByCompradorId(Long id) {
        log.debug("Listando todas os lances do comprador de ID: {}", id);
        return repository.findAllByCompradorIdOrderByValorDesc(id).stream().map(builder::build).collect(Collectors.toList());
    }

    @Override
    public void create(CreateLanceDTO dto) {
        log.debug("Criando novo lance: {}", dto);
        Validators.validate(dto);

        Comprador comprador = compradorService.findDomainById(dto.getCompradorId());
        Oferta oferta = ofertaService.findDomainById(dto.getOfertaId());

        Validators.validateDadosOferta(dto, oferta, log);
        Optional<Lance> ultimoLanceOpt = repository.findFirstByOfertaIdOrderByDhCriacaoDesc(oferta.getId());
        ultimoLanceOpt.ifPresent(lance -> Validators.validateUltimoLance(dto, lance, oferta, log));

        Lance lance = builder.build(dto, comprador, oferta);
        lance = repository.save(lance);
        
        CreateNotificacaoService notificacaoService = notificacaoStrategy.get("lanceRecebido");
        notificacaoService.createNotificacao(lance);
    }
}
