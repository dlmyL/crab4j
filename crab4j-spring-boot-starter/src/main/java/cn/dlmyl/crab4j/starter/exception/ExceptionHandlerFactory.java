package cn.dlmyl.crab4j.starter.exception;

/**
 * 异常处理器工厂
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler provideExceptionHandler() {
        return DefaultExceptionHandler.getInstance();
    }

}