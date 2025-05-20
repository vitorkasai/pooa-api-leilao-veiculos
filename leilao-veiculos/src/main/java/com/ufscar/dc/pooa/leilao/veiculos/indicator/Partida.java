package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Partida {
    ELETRICA("ELÉTRICA"),
    PEDAL("PEDAL"),
    MANUAL("MANUAL");

    private final String value;
}