package com.ufscar.dc.pooa.leilao.veiculos.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Campo {
    String nome();
}