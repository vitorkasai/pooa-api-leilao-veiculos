package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateLanceDTO {
    private Double valor;
    private Long ofertaId;
    private Long compradorId;
}
