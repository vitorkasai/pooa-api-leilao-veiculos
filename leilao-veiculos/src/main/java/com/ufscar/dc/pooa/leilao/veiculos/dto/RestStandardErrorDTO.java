package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RestStandardErrorDTO {
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
    private Integer status;
}
