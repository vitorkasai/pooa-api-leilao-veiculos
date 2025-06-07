package com.ufscar.dc.pooa.leilao.veiculos.repository;

import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanceRepository extends JpaRepository<Lance, Long> {
    Optional<Lance> findFirstByOfertaIdOrderByDhCriacaoDesc(Long ofertaId);
    List<Lance> findAllByOfertaIdOrderByValorDesc(Long ofertaId);
}
