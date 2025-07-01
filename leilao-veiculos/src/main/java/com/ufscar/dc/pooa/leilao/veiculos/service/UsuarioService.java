package com.ufscar.dc.pooa.leilao.veiculos.service;

import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findOptDomainByEmail(String email);
    Optional<Usuario> findOptDomainByDocumento(String documentp);
}
