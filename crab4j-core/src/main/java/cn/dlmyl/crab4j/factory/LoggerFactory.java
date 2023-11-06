package cn.dlmyl.crab4j.factory;

import cn.dlmyl.crab4j.logger.Logger;
import cn.dlmyl.crab4j.logger.SLFJLogger;

/**
 * 日志工厂
 *
 * @author dlmyL
 */
public class LoggerFactory {

    public static Logger create(Class<?> clazz) {
        org.slf4j.Logger slfjLogger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new SLFJLogger(slfjLogger);
    }

    public static Logger create(String loggerName) {
        org.slf4j.Logger slfjLogger = org.slf4j.LoggerFactory.getLogger(loggerName);
        return new SLFJLogger(slfjLogger);
    }

}
