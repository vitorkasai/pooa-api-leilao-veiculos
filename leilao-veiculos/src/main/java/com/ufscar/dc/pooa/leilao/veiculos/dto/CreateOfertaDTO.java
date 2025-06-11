package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateOfertaDTO {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhFim;

    private Double valorInicial;
    private Double valorIncremental;
    private Long vendedorId;
    private Long veiculoId;
    private CreateEnderecoDTO endereco;

    @JsonIgnore
    private Estado estado;
}
