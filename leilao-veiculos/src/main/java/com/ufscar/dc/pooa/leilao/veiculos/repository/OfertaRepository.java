package com.ufscar.dc.pooa.leilao.veiculos.repository;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfertaRepository extends JpaRepository<Oferta, Long>  {
    List<Oferta> findAllByEstado(Estado estado);
}
