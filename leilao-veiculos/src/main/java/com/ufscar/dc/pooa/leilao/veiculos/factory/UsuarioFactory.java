package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.model.Comprador;

public class UsuarioFactory {
    public static Comprador criaComprador() {
        return new Comprador();
    }
}