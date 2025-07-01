package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;
import com.ufscar.dc.pooa.leilao.veiculos.repository.UsuarioRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(CompradorServiceImpl.class);
    private final UsuarioRepository repository;

    public Optional<Usuario> findOptDomainByEmail(String email) {
        log.debug("Buscando usuário: {}", email);
        return repository.findByEmail(email);
    }

    public Optional<Usuario> findOptDomainByDocumento(String document) {
        log.debug("Buscando usuário: {}", document);
        return repository.findByDocumento(document);
    }
}