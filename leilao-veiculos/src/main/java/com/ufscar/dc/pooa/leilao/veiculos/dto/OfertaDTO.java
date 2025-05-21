package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OfertaDTO {
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dhFim;

    private Double valorInicial;
    private Double valorIncremental;
    private Estado estado;
    private Long idVendedor;
    private Long idVeiculo;
    private EnderecoDTO endereco;
}
