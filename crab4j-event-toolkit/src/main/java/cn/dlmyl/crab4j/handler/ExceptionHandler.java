package cn.dlmyl.crab4j.handler;

import cn.dlmyl.crab4j.context.EventContext;

/**
 * 异常处理器
 *
 * @author dlmyL
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
