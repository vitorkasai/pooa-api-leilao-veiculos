package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacaoDTO {
	private Long id;
	private String conteudo;
	private boolean isVisualizado;
}