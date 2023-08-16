package cn.crab4j.starter.exception;

import cn.crab4j.starter.core.listener.EventListener;

/**
 * 异常处理器接口
 *
 * @author dlmyL
 */
public interface ExceptionHandler {

    @SuppressWarnings("rawtypes")
    void handler(EventListener listener, Exception exception);

}
