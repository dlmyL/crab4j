package cn.dlmyl.crab4j.starter.logger;

/**
 * 日志接口
 *
 * @author dlmyL
 */
public interface Logger {

    void debug(String msg);

    default void debug(String msg, Object... args){
        debug(String.format(msg, args));
    }


    void info(String msg);

    default void info(String msg, Object... args){
        info(String.format(msg, args));
    }


    void warn(String msg);

    default void warn(String msg, Object... args){
        warn(String.format(msg, args));
    }


    void error(String msg);

    default void error(String msg, Object... args){
        error(String.format(msg, args));
    }

    void error(String msg, Throwable t);

}
