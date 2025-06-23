package com.ufscar.dc.pooa.leilao.veiculos.model;

import com.ufscar.dc.pooa.leilao.veiculos.framework.Campo;
import com.ufscar.dc.pooa.leilao.veiculos.framework.Tabela;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Tabela(nome = "LEILAO_VEICULOS.TB_DIVULGACAO")
public class Divulgacao {
	@Campo(nome = "UID")
	private String uid;
	
	@Campo(nome = "tx_nome")
	private String nome;
	
	@Campo(nome = "tx_link")
	private String link;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}