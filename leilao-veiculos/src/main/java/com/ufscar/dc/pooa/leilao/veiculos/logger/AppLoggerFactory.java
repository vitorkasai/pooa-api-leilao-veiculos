package com.ufscar.dc.pooa.leilao.veiculos.logger;

public class AppLoggerFactory {
    public static AppLogger getAppLogger(Class<?> clazz) {
        return new AppLoggerImpl(clazz);
    }
}
