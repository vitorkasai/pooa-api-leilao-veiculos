package com.ufscar.dc.pooa.leilao.veiculos.builder;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateCompradorDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;

@Component
public class CompradorBuilder {
    public Comprador build(CreateCompradorDTO dto) {
        Comprador comprador = new Comprador();
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

    public CreateCompradorDTO build(Comprador entity) {
        CreateCompradorDTO compradorDTO = new CreateCompradorDTO();
        compradorDTO.setNome(entity.getNome());
        compradorDTO.setSobrenome(entity.getSobrenome());
        compradorDTO.setEmail(entity.getEmail());
        compradorDTO.setTelefone(entity.getTelefone());
        compradorDTO.setDocumento(entity.getDocumento());
        compradorDTO.setInteresse(entity.getInteresse());
        compradorDTO.setDataNascimento(entity.getDataNascimento());
        return compradorDTO;
    }
}