package com.ufscar.dc.pooa.leilao.veiculos.repository;

import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByDocumento(String document);
}
