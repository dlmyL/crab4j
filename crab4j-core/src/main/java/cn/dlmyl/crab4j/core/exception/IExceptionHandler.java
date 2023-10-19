package cn.dlmyl.crab4j.core.exception;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.spi.ServiceLoaders;

/**
 * 异常处理器
 *
 * @author dlmyL
 */
public interface IExceptionHandler {

    IExceptionHandler DEFAULT = ServiceLoaders.loadDefault(IExceptionHandler.class);

    /**
     * 异常处理
     *
     * @param cause 异常原因
     * @param context 事件上下文
     */
    void handle(Throwable cause, IEventContext context);

}
