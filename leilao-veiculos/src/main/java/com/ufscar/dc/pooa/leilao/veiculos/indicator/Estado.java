package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Estado {
    NAO_INICIADO("NÃO_INICIADO"),
    EM_ANDAMENTO("EM_ANDAMENTO"),
    FINALIZADO("FINALIZADO"),
    CANCELADO("CANCELADO");

    private final String value;
}