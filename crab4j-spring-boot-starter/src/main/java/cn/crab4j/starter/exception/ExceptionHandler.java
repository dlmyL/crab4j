package cn.crab4j.starter.exception;

import cn.crab4j.starter.core.listener.EventListener;

/**
 * ExceptionHandler
 *
 * @author dlmyL
 */
public interface ExceptionHandler {

    void handler(EventListener listener, Exception exception);

}
