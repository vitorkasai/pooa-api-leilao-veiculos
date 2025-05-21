package com.ufscar.dc.pooa.leilao.veiculos.builder;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.factory.VeiculoFactory;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Partida;
import com.ufscar.dc.pooa.leilao.veiculos.model.Moto;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MotoBuilder {

    public Veiculo build(VeiculoDTO dto) {
        Moto moto = VeiculoFactory.criaMoto();
        moto.setModelo(dto.getModelo());
        moto.setDhCriacao(LocalDateTime.now());
        moto.setPlaca(dto.getPlaca());
        moto.setCor(dto.getCor());
        moto.setRenavam(dto.getRenavam());
        moto.setQuilometragem(dto.getQuilometragem());
        moto.setDescricao(dto.getDescricao());
        moto.setCilindrada(dto.getCilindrada());
        moto.setMarcha(dto.getMarcha());
        moto.setPartida(Partida.valueOf(dto.getPartida().toUpperCase()));
        return moto;
    }
}