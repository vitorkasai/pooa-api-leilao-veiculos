package com.ufscar.dc.pooa.leilao.veiculos.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ufscar.dc.pooa.leilao.veiculos.framework.PersistenciaCampo;
import com.ufscar.dc.pooa.leilao.veiculos.framework.PersistenciaTabela;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PersistenciaTabela(schema = "LEILAO_VEICULOS", nome = "TB_DIVULGACAO")
public class Divulgacao {
	@PersistenciaCampo(nome = "UID_DIVULGACAO")
	private String uid;
	
	@PersistenciaCampo(nome = "TX_NOME")
	private String nome;
	
	@PersistenciaCampo(nome = "TX_LINK")
	private String link;
	
	@PersistenciaCampo(nome = "DH_CRIACAO")
	private LocalDateTime dhCriacao;
}