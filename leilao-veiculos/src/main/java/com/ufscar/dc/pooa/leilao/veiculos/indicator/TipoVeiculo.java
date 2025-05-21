package com.ufscar.dc.pooa.leilao.veiculos.indicator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoVeiculo {
    CARRO("CARRO"),
    MOTO("MOTO");

    private final String value;
}