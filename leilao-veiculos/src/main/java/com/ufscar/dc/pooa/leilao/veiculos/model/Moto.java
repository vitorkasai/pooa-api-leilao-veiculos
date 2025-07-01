package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Partida;

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
@DiscriminatorValue("MOTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto extends Veiculo{
    @Column(name = "CILINDRADA")
    private int cilindrada;

    @Column(name = "MARCHA")
    private int marcha;

    @Enumerated(EnumType.STRING)
    @Column(name = "PARTIDA")
    private Partida partida;
}
