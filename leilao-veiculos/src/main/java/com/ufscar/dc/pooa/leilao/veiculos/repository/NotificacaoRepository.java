package com.ufscar.dc.pooa.leilao.veiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ufscar.dc.pooa.leilao.veiculos.model.Notificacao;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
	@Query(value = "SELECT * FROM LEILAO_VEICULOS.TB_NOTIFICACAO "
			+ "WHERE ID_USUARIO = ?1 AND IS_VISUALIZADO = FALSE "
			+ "ORDER BY DH_CRIACAO DESC", nativeQuery = true)
	List<Notificacao> findAllByUserId(Long id);
}