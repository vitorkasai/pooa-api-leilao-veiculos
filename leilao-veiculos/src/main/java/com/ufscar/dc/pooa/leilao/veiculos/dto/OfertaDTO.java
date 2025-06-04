package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaDTO {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dhFim;

    private Double valorInicial;
    private Double valorIncremental;
    private Long idVendedor;
    private Long idVeiculo;
    private EnderecoDTO endereco;

    @JsonIgnore
    private Estado estado;
}
