package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.DivulgacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.DivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.framework.PersistenciaFramework;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Divulgacao;
import com.ufscar.dc.pooa.leilao.veiculos.service.DivulgacaoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DivulgacaoServiceImpl implements DivulgacaoService {
    private static final AppLogger log = AppLoggerFactory.getAppLogger(DivulgacaoServiceImpl.class);
	private final DivulgacaoBuilder builder;
	private PersistenciaFramework framework = new PersistenciaFramework();
	
	@Override
	public void create(DivulgacaoDTO dto) {
		log.debug("Criando novo link de divulgação: {}", dto);
		validate(dto);
		Divulgacao divulgacao = builder.build(dto);
		framework.save(divulgacao);
	}
	
	private static void validate(DivulgacaoDTO dto) {
		Optional.ofNullable(dto.getNome()).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
		Optional.ofNullable(dto.getLink()).orElseThrow(() -> new BadRequestException("Campo link é obrigatório"));
	}
}
