package com.ufscar.dc.pooa.leilao.veiculos.logger;

public interface AppLogger {
    public void debug(String message);
    public void debug(String message, Object object);
    public void info(String message);
    public void info(String message, Object object);
    public void warn(String message);
    public void warn(String message, Object object);
    public void error(String message);
    public void error(String message, Object object);
}
