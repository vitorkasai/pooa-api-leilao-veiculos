package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ufscar.dc.pooa.leilao.veiculos.builder.DivulgacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnDivulgacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.BadRequestException;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
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
	private final PersistenciaFramework framework;

	@Override
	public ReturnDivulgacaoDTO findByUid(String uid) {
		log.debug("Buscando divulgação: {}", uid);
		Divulgacao divulgacao = (Divulgacao) framework.findOneBy(Divulgacao.class, "uid", uid)
				.orElseThrow(() -> new NotFoundException("Falha ao validar divulgação de uid: " + uid));
		return builder.build(divulgacao);
	}

	@Override
	public List<ReturnDivulgacaoDTO> findAll() {
		log.debug("Buscando todas divulgações");
		return framework.findAll(Divulgacao.class).stream()
				.map(divulgacao -> builder.build((Divulgacao) divulgacao))
				.toList();
	}

	@Override
	public void create(CreateDivulgacaoDTO dto) {
		log.debug("Criando novo link de divulgação: {}", dto);
		validate(dto);
		Divulgacao divulgacao = builder.build(dto);
		framework.save(divulgacao);
	}

	@Override
	public void delete(String uid) {
		log.debug("Deletando link de divulgação: {}", uid);
		if (!framework.doesExist(Divulgacao.class, "uid", uid)) {
			log.error("Falha ao validar divulgação com uid {} ao deletar", uid);
			throw new NotFoundException("Falha ao validar divulgação de uid: " + uid);
		}
		framework.delete(Divulgacao.class, "uid", uid);
	}
	
	private static void validate(CreateDivulgacaoDTO dto) {
		Optional.ofNullable(dto.getNome()).orElseThrow(() -> new BadRequestException("Campo nome é obrigatório"));
		Optional.ofNullable(dto.getLink()).orElseThrow(() -> new BadRequestException("Campo link é obrigatório"));
	}
}
