package cn.dlmyl.crab4j.core.event;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.exception.IExceptionHandler;

/**
 * 简单的异常处理器
 *
 * @author dlmyL
 */
public class SimpleExceptionHandler implements IExceptionHandler {

    @Override
    public void handle(Throwable cause, IEventContext context) {
        System.out.println("====================================");
        System.out.printf("error message: %s %n", cause.getMessage());
        System.out.println("====================================");
        System.out.printf("className: %s %n", context.getSubscriber().getClass());
        System.out.printf("methodName: %s %n", context.getSubscribe().getName());
        System.out.printf("busName: %s %n", context.getSource());
        System.out.printf("eventMessage: %s %n", context.getEvent());
        System.out.println("====================================");
    }

}
