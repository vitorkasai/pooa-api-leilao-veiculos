package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.UsuarioPapel;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompradorBuilder {
    public Comprador build(CompradorDTO dto) {
        Comprador comprador = new Comprador();
        comprador.setPapel(UsuarioPapel.COMPRADOR.getValue());
        comprador.setNome(dto.getNome());
        comprador.setSobrenome(dto.getSobrenome());
        comprador.setEmail(dto.getEmail());
        comprador.setTelefone(dto.getTelefone());
        comprador.setDocumento(dto.getDocumento());
        comprador.setInteresse(dto.getInteresse());
        comprador.setDataNascimento(dto.getDataNascimento());
        comprador.setDhCriacao(LocalDateTime.now());
        return comprador;
    }
}