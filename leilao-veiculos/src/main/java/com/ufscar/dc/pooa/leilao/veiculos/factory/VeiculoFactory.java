package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.model.Carro;
import com.ufscar.dc.pooa.leilao.veiculos.model.Moto;
import com.ufscar.dc.pooa.leilao.veiculos.model.Veiculo;


public abstract class VeiculoFactory {
    static Veiculo criarMoto(
    ) {
        return new Moto();
    }

    static Veiculo criarCarro(
    ) {
        return new Carro();
    }

}



