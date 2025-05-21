package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Moto;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Cambio;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Direcao;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Partida;

import java.time.LocalDateTime;

public class VeiculoFactory {

    public static Carro criarCarro(VeiculoDTO dto) {
        return new Carro(
                dto.getModelo(),
                LocalDateTime.now(),
                dto.getPlaca(),
                dto.getCor(),
                dto.getRenavam(),
                dto.getQuilometragem(),
                dto.getDescricao(),
                dto.getNumeroPortas(),
                Direcao.valueOf(dto.getDirecao()),
                Cambio.valueOf(dto.getCambio())
        );
    }

    public static Moto criarMoto(VeiculoDTO dto) {
        return new Moto(
                dto.getModelo(),
                LocalDateTime.now(),
                dto.getPlaca(),
                dto.getCor(),
                dto.getRenavam(),
                dto.getQuilometragem(),
                dto.getDescricao(),
                dto.getCilindrada(),
                dto.getMarcha(),
                Partida.valueOf(dto.getPartida())
        );
    }
}
