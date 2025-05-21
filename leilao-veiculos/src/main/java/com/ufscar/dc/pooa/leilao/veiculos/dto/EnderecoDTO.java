package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
}
