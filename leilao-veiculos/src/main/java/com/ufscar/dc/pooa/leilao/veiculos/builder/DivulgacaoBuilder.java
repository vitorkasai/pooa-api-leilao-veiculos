package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Divulgacao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DivulgacaoBuilder {
	public Divulgacao build(CreateDivulgacaoDTO dto) {
		Divulgacao divulgacao = new Divulgacao();
		divulgacao.setUid(UUID.randomUUID().toString());
		divulgacao.setNome(dto.getNome());
		divulgacao.setLink(dto.getLink());
		divulgacao.setDhCriacao(LocalDateTime.now());
		return divulgacao;
	}

	public ReturnDivulgacaoDTO build(Divulgacao divulgacao) {
		ReturnDivulgacaoDTO divulgacaoDTO = new ReturnDivulgacaoDTO();
		divulgacaoDTO.setUid(divulgacao.getUid());
		divulgacaoDTO.setNome(divulgacao.getNome());
		divulgacaoDTO.setLink(divulgacao.getLink());
		return divulgacaoDTO;
	}
}
