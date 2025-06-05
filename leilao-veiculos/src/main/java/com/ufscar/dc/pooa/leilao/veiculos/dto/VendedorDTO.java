package com.ufscar.dc.pooa.leilao.veiculos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VendedorDTO extends UsuarioDTO {
	private String nomeFantasia;
	private String contaBancaria;
}