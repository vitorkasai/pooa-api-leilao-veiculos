package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReturnNotificacaoDTO {
	private Long id;
	private String conteudo;
	private boolean isVisualizado;
}