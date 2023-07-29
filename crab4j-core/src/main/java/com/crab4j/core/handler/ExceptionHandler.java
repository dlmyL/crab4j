package com.crab4j.core.handler;

import com.crab4j.core.context.EventContext;

/**
 * 事件异常处理器
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface ExceptionHandler {

    /**
     * 异常处理
     *
     * @param cause 异常原因
     * @param context 事件上下文
     */
    void handle(Throwable cause, EventContext context);

}
