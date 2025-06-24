package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.DivulgacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
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
	private final PersistenciaFramework framework = new PersistenciaFramework();
	
	@Override
	public void create(CreateDivulgacaoDTO dto) {
		log.debug("Criando novo link de divulgação: {}", dto);
		validate(dto);
		Divulgacao divulgacao = builder.build(dto);
		framework.save(divulgacao);
	}

	@Override
	public void delete(UUID uid) {
		log.debug("Deletando link de divulgação: {}", uid);
		if (!framework.doesExist(Divulgacao.class, "uid", uid)) {
			log.error("Falha ao validar divulgação com id {} ao deletar", uid);
			throw new NotFoundException("Falha ao validar divulgação de id: " + uid);
		}
		framework.delete(Divulgacao.class, "uid", uid);
	}
	
	private static void validate(CreateDivulgacaoDTO dto) {
		Optional.ofNullable(dto.getNome()).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
		Optional.ofNullable(dto.getLink()).orElseThrow(() -> new BadRequestException("Campo link é obrigatório"));
	}
}
