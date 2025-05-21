package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OfertaDTO {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Double valorInicial;
    private Integer valorIncremental;
    private Estado estado;
    private Integer idVendedor;
    private Integer idVeiculo;
    private EnderecoDTO endereco;
}
