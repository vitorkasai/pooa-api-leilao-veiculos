package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direcao {
    DIRECAO_A_ESQUERDA("DIREÇÃO À ESQUERDA"),
    DIRECAO_A_DIREITA("DIREÇÃO À DIREITA");

    private final String value;
}