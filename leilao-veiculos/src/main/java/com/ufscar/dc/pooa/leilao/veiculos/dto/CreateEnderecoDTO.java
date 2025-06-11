package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateEnderecoDTO {
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
}
