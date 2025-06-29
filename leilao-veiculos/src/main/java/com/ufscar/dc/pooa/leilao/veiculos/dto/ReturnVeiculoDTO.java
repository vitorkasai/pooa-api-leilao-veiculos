package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnVeiculoDTO {
    private String tipoVeiculo;
    private String modelo;
    private String placa;
    private String cor;
    private String renavam;
    private Integer quilometragem;
    private String descricao;
}
