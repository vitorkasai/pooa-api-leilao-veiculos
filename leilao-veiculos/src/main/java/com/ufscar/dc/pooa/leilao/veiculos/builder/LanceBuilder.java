package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.dto.ReturnLanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LanceBuilder {
    public Lance build(CreateLanceDTO dto, Comprador comprador, Oferta oferta) {
        Lance lance = new Lance();
        lance.setValor(dto.getValor());
        lance.setDhCriacao(LocalDateTime.now());
        lance.setOferta(oferta);
        lance.setComprador(comprador);
        return lance;
    }

    public ReturnLanceDTO build(Lance entity) {
        ReturnLanceDTO lanceDTO = new ReturnLanceDTO();
        lanceDTO.setId(entity.getId());
        lanceDTO.setValor(entity.getValor());
        lanceDTO.setOfertaId(entity.getOferta().getId());
        lanceDTO.setCompradorId(entity.getComprador().getId());
        lanceDTO.setData(entity.getDhCriacao());
        return lanceDTO;
    }
}
