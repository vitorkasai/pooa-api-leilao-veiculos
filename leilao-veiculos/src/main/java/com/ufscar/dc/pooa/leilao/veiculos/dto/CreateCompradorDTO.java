package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCompradorDTO extends CreateUsuarioDTO {
    private String interesse;
    private String dataNascimento;
}