package com.ufscar.dc.pooa.leilao.veiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Leiloeiro extends Vendedor {
    @Column(name = "ORGAO_REGULADOR", length = 100)
    private String orgaoRegulador;
}
