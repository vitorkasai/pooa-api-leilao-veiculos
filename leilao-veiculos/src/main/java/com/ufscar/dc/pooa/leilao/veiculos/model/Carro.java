package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Cambio;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Direcao;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("CARRO")
@NoArgsConstructor
@AllArgsConstructor
public class Carro extends Veiculo {
    @Column(name = "NUMERO_PORTAS")
    private int numeroPortas;

    @Enumerated(EnumType.STRING)
    @Column(name = "DIRECAO")
    private Direcao direcao;

    @Enumerated(EnumType.STRING)
    @Column(name = "CAMBIO")
    private Cambio cambio;
}
