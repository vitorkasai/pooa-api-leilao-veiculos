package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Moto;


public class VeiculoFactory {

    public static Carro criaCarro() {
        return new Carro();
    }

    public static Moto criaMoto() {
        return new Moto();
    }
}
