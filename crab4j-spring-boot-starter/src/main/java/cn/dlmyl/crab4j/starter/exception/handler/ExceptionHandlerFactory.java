package cn.dlmyl.crab4j.starter.exception.handler;

/**
 * 异常处理器工厂
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler provideExceptionHandler() {
        return DefaultExceptionHandler.X;
    }

}