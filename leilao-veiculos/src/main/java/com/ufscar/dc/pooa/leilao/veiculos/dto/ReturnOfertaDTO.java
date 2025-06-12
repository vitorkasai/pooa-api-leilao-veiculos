package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnOfertaDTO {
    private Long id;
    private Double valorInicial;
    private Double valorIncremental;
    private Estado estado;
    private CreateEnderecoDTO endereco;
    private Long vendedorId;
    private Long veiculoId;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhFim;
}
