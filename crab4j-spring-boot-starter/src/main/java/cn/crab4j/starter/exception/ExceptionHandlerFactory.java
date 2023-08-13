package cn.crab4j.starter.exception;

/**
 * ExceptionHandlerFactory
 *
 * @author dlmyL
 */
public class ExceptionHandlerFactory {

    public static ExceptionHandler create() {
        return DefaultExceptionHandler.singleton;
    }

}