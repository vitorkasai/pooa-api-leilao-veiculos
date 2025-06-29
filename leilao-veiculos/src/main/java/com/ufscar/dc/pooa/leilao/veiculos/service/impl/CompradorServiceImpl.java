package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.CompradorBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.encryption.EncryptionService;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.repository.CompradorRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.CompradorService;
import com.ufscar.dc.pooa.leilao.veiculos.util.Validators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompradorServiceImpl implements CompradorService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final CompradorRepository repository;
    private final CompradorBuilder builder;
    private final EncryptionService encryptionService;

    @Override
    public Comprador findDomainById(Long id) {
        log.debug("Buscando comprador: {}", id);
        Comprador comprador = repository.findById(id).orElseThrow(() -> new NotFoundException("Falha ao validar comprador de id: " + id));

        comprador.setDocumento(encryptionService.decrypt(comprador.getDocumento()));

        return  comprador;
    }

    @Override
    public void create(CreateCompradorDTO dto) {
        log.debug("Criando novo comprador: {}", dto);
        Validators.validate(dto);

        dto.setDocumento(encryptionService.encrypt(dto.getDocumento()));

        repository.save(builder.build(dto));
    }
}