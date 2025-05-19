package com.ufscar.dc.pooa.leilao.veiculos.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLoggerImpl implements AppLogger {
    private final Logger logger;

    public AppLoggerImpl(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void debug(String message, Object object) {
        logger.debug(message, object);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Object object) {
        logger.info(message, object);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void warn(String message, Object object) {
        logger.warn(message, object);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Object object) {
        logger.error(message, object);
    }
}
