package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
public class RestStandardErrorDTO {
    private String error;
    private String message;
    private String path;
    private Instant timestamp;
    private Integer status;
}
