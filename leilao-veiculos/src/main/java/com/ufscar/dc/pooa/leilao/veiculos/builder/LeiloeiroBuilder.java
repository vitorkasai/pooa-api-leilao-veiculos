package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLeiloeiroDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Leiloeiro;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LeiloeiroBuilder {
    public Leiloeiro build(CreateLeiloeiroDTO dto) {
        Leiloeiro leiloeiro = new Leiloeiro();
        leiloeiro.setNome(dto.getNome());
        leiloeiro.setSobrenome(dto.getSobrenome());
        leiloeiro.setEmail(dto.getEmail());
        leiloeiro.setTelefone(dto.getTelefone());
        leiloeiro.setDocumento(dto.getDocumento());
        leiloeiro.setNomeFantasia(dto.getNomeFantasia());
        leiloeiro.setContaBancaria(dto.getContaBancaria());
        leiloeiro.setOrgaoRegulador(dto.getOrgaoRegulador());
        leiloeiro.setDhCriacao(LocalDateTime.now());
        return leiloeiro;
    }
}
