package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.LeiloeiroBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLeiloeiroDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Leiloeiro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;
import com.ufscar.dc.pooa.leilao.veiculos.repository.LeiloeiroRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.LeiloeiroService;
import com.ufscar.dc.pooa.leilao.veiculos.service.UsuarioService;
import com.ufscar.dc.pooa.leilao.veiculos.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LeiloeiroServiceImpl implements LeiloeiroService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final UsuarioService usuarioService;
    private final LeiloeiroRepository repository;
    private final LeiloeiroBuilder builder;

    @Override
    public Leiloeiro findDomainById(Long id) {
        log.debug("Buscando leiloeiro: {}", id);
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar leiloeiro de id: " + id));
    }

    @Override
    public void create(CreateLeiloeiroDTO dto) {
        log.debug("Criando novo leiloeiro: {}", dto);
        ValidatorUtil.validate(dto);
        usuarioService.findOptDomainByEmail(dto.getEmail()).ifPresent((Usuario usuario) -> {
            throw new BadRequestException("Falha ao criar leiloeiro, já existe um com o email " + dto.getEmail());
        });
        usuarioService.findOptDomainByDocumento(dto.getEmail()).ifPresent((Usuario usuario) -> {
            throw new BadRequestException("Falha ao criar leiloeiro, já existe um com o documento " + dto.getDocumento());
        });
        repository.save(builder.build(dto));
    }
}
