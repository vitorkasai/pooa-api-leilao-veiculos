package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioDTO {
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String documento;
}
