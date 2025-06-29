package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CreateOfertaDTO {
    private Double valorInicial;
    private Double valorIncremental;
    private Long vendedorId;
    private Long veiculoId;
    private CreateVeiculoDTO veiculo;
    private CreateEnderecoDTO endereco;

    @JsonIgnore
    private Estado estado;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhFim;
}
