package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompradorDTO extends UsuarioDTO {
    private String interesse;
    private String dataNascimento;
}