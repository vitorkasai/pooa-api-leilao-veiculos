package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Moto;


public class VeiculoFactory {

    public static Carro criarCarro() {
        return new Carro();
    }

    public static Moto criarMoto() {
        return new Moto();
    }
}
