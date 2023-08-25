package cn.dlmyl.crab4j.exception;

import cn.dlmyl.crab4j.context.EventContext;

/**
 * 异常处理器
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
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
