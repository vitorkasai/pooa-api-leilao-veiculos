package com.ufscar.dc.pooa.leilao.veiculos.factory;

import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLogger;
import com.ufscar.dc.pooa.leilao.veiculos.logger.AppLoggerImpl;

public class AppLoggerFactory {
    public static AppLogger getAppLogger(Class<?> clazz) {
        return new AppLoggerImpl(clazz);
    }
}
