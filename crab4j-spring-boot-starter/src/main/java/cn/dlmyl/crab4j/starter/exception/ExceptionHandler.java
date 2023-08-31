package cn.dlmyl.crab4j.starter.exception;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;

/**
 * 异常处理器接口
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public interface ExceptionHandler {

    @SuppressWarnings("rawtypes")
    void handler(EventListener listener, Exception exception);

}