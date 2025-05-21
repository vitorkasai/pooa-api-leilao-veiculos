package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direcao {
    MANUAL("MANUAL"),
    HIDRAULICA("HIDRÁULICA"),
    ELETRICA("ELÉTRICA"),
    ELETRO_HIDRAULICA("ELETRO-HIDRÁULICA");

    private final String value;
}