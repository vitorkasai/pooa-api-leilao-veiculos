package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.LanceDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;
import com.ufscar.dc.pooa.leilao.veiculos.model.Lance;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LanceBuilder {
    public Lance build(LanceDTO dto, Comprador comprador, Oferta oferta) {
        Lance lance = new Lance();
        lance.setValor(dto.getValor());
        lance.setDhCriacao(LocalDateTime.now());
        lance.setOferta(oferta);
        lance.setComprador(comprador);
        return lance;
    }
}
