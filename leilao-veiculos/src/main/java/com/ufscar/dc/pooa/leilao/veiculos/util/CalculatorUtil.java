package com.ufscar.dc.pooa.leilao.veiculos.util;

import com.ufscar.dc.pooa.leilao.veiculos.indicator.Estado;
import com.ufscar.dc.pooa.leilao.veiculos.model.Oferta;

import java.time.LocalDateTime;

public class CalculatorUtil {
    public static Estado calculateEstado(Oferta oferta) {
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isBefore(oferta.getDhInicio())) {
            return Estado.NAO_INICIADO;
        } else if (!agora.isAfter(oferta.getDhFim())) {
            return Estado.EM_ANDAMENTO;
        } else {
            return Estado.FINALIZADO;
        }
    }
}
