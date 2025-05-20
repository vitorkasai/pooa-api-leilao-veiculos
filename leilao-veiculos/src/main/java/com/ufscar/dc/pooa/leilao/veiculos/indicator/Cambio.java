package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Cambio {
    MANUAL("MANUAL"),
    AUTOMATICO("AUTOMÁTICO"),
    SEMIAUTOMATICO("SEMIAUTOMÁTICO");

    private final String value;
}