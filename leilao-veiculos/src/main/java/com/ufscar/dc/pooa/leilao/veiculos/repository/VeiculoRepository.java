package com.ufscar.dc.pooa.leilao.veiculos.repository;

import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}