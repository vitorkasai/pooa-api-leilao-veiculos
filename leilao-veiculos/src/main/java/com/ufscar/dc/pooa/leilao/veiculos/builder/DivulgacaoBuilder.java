package com.ufscar.dc.pooa.leilao.veiculos.builder;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Divulgacao;

@Component
public class DivulgacaoBuilder {
	public Divulgacao build(CreateDivulgacaoDTO dto) {
		Divulgacao divulgacao = new Divulgacao();
		divulgacao.setUid(UUID.randomUUID());
		divulgacao.setNome(dto.getNome());
		divulgacao.setLink(dto.getLink());
		divulgacao.setDhCriacao(LocalDateTime.now());
		return divulgacao;
	}
}
