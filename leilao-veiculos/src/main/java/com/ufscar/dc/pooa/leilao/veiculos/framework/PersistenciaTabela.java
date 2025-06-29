package com.ufscar.dc.pooa.leilao.veiculos.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PersistenciaTabela {
    String nome();
    String schema() default "";
}