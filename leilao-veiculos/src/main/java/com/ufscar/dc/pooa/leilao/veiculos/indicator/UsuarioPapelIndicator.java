package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsuarioPapelIndicator {
    COMPRADOR("COMPRADOR"),
    VENDEDOR("VENDEDOR"),
    LEILOEIRO("LEILOEIRO");
    private final String value;
}