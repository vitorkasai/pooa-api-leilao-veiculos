package com.ufscar.dc.pooa.leilao.veiculos.service.impl;

import com.ufscar.dc.pooa.leilao.veiculos.builder.NotificacaoBuilder;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnNotificacaoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.exception.NotFoundException;
import com.ufscar.dc.pooa.leilao.veiculos.factory.AppLoggerFactory;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.model.Notificacao;
import com.ufscar.dc.pooa.leilao.veiculos.repository.NotificacaoRepository;
import com.ufscar.dc.pooa.leilao.veiculos.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService {
	private static final AppLogger log = AppLoggerFactory.getAppLogger(NotificacaoServiceImpl.class);

	private final NotificacaoRepository repository;
	private final NotificacaoBuilder builder;
	
	@Override
	public List<ReturnNotificacaoDTO> findAllByUsuarioId(Long usuarioId) {
		log.debug("Listando todas as notificações não visualizadas do usuário de ID: {}", usuarioId);
		return repository.findAllByUserId(usuarioId).stream().map(builder::build).collect(Collectors.toList());
	}
	
	@Override
	public void view(Long notificacaoId) {
		log.debug("Visualizando a notificação de ID: {}", notificacaoId);
		Notificacao notificacao = repository.findById(notificacaoId).orElseThrow(() -> new NotFoundException("Falha ao validar notificação"));
		builder.buildVisualizar(notificacao);
		repository.save(notificacao);
		log.debug("Notificação de id {} visualizada com sucesso", notificacaoId);
	}
}