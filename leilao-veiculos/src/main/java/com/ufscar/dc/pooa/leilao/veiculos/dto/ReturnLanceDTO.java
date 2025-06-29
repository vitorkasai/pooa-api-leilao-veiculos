package com.ufscar.dc.pooa.leilao.veiculos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReturnLanceDTO {
    private Long id;
    private Double valor;
    private ReturnOfertaDTO oferta;
    private CreateCompradorDTO comprador;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime data;
}
