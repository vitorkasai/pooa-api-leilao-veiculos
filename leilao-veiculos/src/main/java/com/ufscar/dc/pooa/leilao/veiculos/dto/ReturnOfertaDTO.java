package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReturnOfertaDTO {
    private Long id;
    private Double valorInicial;
    private Double valorIncremental;
    private Estado estado;
    private CreateEnderecoDTO endereco;
    private CreateVendedorDTO vendedor;
    private ReturnVeiculoDTO veiculo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dhFim;
}
