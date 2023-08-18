package cn.dlmyl.crab4j.starter.exception;

/**
 * 异常处理器工厂
 *
 * @author dlmyL
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler provideExceptionHandler() {
        return DefaultExceptionHandler.getInstance();
    }

}