package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateVeiculoDTO {
    private String tipoVeiculo;
    private String modelo;
    private String placa;
    private String cor;
    private String renavam;
    private Integer quilometragem;
    private String descricao;

    private Integer numeroPortas;
    private String direcao;
    private String cambio;

    private Integer cilindrada;
    private Integer marcha;
    private String partida;
}
