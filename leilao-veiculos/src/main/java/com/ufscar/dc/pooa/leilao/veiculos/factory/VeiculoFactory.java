package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.dto.VeiculoDTO;
<<<<<<< HEAD
=======
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Cambio;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Direcao;
import com.ufscar.dc.pooa.leilao.veiculos.indicator.Partida;
>>>>>>> ff9b94bb7371dba0685219fd2d5ebf784906e5c0
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
