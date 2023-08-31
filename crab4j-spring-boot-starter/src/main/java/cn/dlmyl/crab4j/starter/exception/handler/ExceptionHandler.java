package cn.dlmyl.crab4j.starter.exception.handler;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;

/**
 * 异常处理器接口
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
public interface ExceptionHandler {

    @SuppressWarnings("rawtypes")
    void handler(EventListener listener, Exception exception);

}
