package cn.dlmyl.crab4j.exception;

/**
 * 异常处理器
 *
 * @author dlmyL
 */
public interface ExceptionHandler<T> {

    void handle(Throwable cause, T context);

}
