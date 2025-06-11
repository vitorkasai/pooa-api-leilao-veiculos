package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateVendedorDTO extends CreateUsuarioDTO {
	private String nomeFantasia;
	private String contaBancaria;
}