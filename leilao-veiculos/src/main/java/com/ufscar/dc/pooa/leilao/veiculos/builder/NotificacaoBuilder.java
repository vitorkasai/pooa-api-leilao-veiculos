package com.ufscar.dc.pooa.leilao.veiculos.builder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ufscar.dc.pooa.leilao.veiculos.model.Notificacao;
import com.ufscar.dc.pooa.leilao.veiculos.model.Usuario;

@Component
public class NotificacaoBuilder {
	public Notificacao build(String conteudo, Usuario usuario) {
		Notificacao notificacao = new Notificacao();
		notificacao.setConteudo(conteudo);
		notificacao.setVisualizado(false);
		notificacao.setUsuario(usuario);
		notificacao.setDhCriacao(LocalDateTime.now());
		return notificacao;
	}
}