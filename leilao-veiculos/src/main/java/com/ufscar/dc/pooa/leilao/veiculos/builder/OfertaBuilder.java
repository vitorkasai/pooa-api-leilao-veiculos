package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.CreateOfertaDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Endereco;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import com.ufscar.dc.pooa.leilao.veiculos.model.Vendedor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OfertaBuilder {
    public Oferta build(CreateOfertaDTO dto, Vendedor vendedor, Veiculo veiculo, Endereco endereco) {
        Oferta oferta = new Oferta();
        oferta.setDhInicio(dto.getDhInicio());
        oferta.setDhFim(dto.getDhFim());
        oferta.setDhCriacao(LocalDateTime.now());
        oferta.setValorInicial(dto.getValorInicial());
        oferta.setValorIncremental(dto.getValorIncremental());
        oferta.setEstado(dto.getEstado());
        oferta.setVendedor(vendedor);
        oferta.setVeiculo(veiculo);
        oferta.setEndereco(endereco);
        return oferta;
    }
}
