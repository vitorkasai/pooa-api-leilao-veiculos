package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.LanceBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.repository.LanceRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import com.ufscar.dc.pooa.leilao.veiculos.service.CreateNotificacaoService;
import com.ufscar.dc.pooa.leilao.veiculos.service.LanceService;
import com.ufscar.dc.pooa.leilao.veiculos.service.OfertaService;
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
    public void create(CreateLanceDTO dto) {
        log.debug("Criando novo lance: {}", dto);
        validate(dto);

        Comprador comprador = compradorService.findDomainById(dto.getCompradorId());
        Oferta oferta = ofertaService.findDomainById(dto.getOfertaId());

        validateDadosOferta(dto, oferta);
        Optional<Lance> ultimoLanceOpt = repository.findFirstByOfertaIdOrderByDhCriacaoDesc(oferta.getId());
        ultimoLanceOpt.ifPresent(lance -> validateUltimoLance(dto, lance, oferta));

        Lance lance = builder.build(dto, comprador, oferta);
        repository.save(lance);
        
        CreateNotificacaoService notificacaoService = notificacaoStrategy.get("lanceRecebido");
        notificacaoService.createNotificacao(lance);
    }

    private static void validate(CreateLanceDTO dto) {
        Optional.ofNullable(dto.getValor()).orElseThrow(() -> new BadRequestException("Campo valor é obrigatório"));
        Optional.ofNullable(dto.getCompradorId()).orElseThrow(() -> new BadRequestException("Campo compradorId é obrigatório"));
        Optional.ofNullable(dto.getOfertaId()).orElseThrow(() -> new BadRequestException("Campo ofertaId é obrigatório"));
    }

    private static void validateDadosOferta(CreateLanceDTO dto, Oferta oferta) {
        if (oferta.getEstado() != Estado.EM_ANDAMENTO) {
            log.error("Falha ao validar oferta com estado {} ao realizar lance", oferta.getEstado());
            throw new BadRequestException("Falha ao validar oferta ao realizar lance");
        }
        if (dto.getValor() < oferta.getValorInicial()) {
            log.error("O valor deve ser maior que o valor inicial da oferta: " + oferta.getValorInicial());
            throw new BadRequestException("O valor deve ser maior que o valor inicial da oferta");
        }
    }

    private static void validateUltimoLance(CreateLanceDTO dto, Lance ultimoLance, Oferta oferta) {
        Double valorUltimoLance = ultimoLance.getValor();
        if (valorUltimoLance > dto.getValor()) {
            log.error("O valor deve ser maior que o valor do último lance feito: " + oferta.getEstado());
            throw new BadRequestException("O valor deve ser maior que o valor do último lance feito");
        }
        if ((dto.getValor() - valorUltimoLance) < oferta.getValorIncremental()) {
            log.error("Diferença entre o valor e o valor do último lance deve ser maior que o valorIncremental: " + oferta.getValorIncremental());
            throw new BadRequestException("Diferença entre o valor e o valor do último lance deve ser maior que o valorIncremental");
        }
        if (dto.getCompradorId().equals(ultimoLance.getComprador().getId())) {
            log.error("O mesmo comprador não pode realizar dois lances consecutivos. Comprador ID: " + dto.getCompradorId());
            throw new BadRequestException("O mesmo comprador não pode realizar dois lances consecutivos");
        }
    }
}
