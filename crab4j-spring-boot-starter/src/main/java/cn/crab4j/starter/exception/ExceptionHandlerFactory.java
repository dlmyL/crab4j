package cn.crab4j.starter.exception;

/**
 * 异常处理器工厂
 *
 * @author dlmyL
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler getInstance() {
        return DefaultExceptionHandler.singleton;
    }

}