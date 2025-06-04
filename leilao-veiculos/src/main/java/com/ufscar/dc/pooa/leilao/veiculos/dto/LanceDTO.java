package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LanceDTO {
    private Double valor;
    private Long idOferta;
    private Long idComprador;
}
