package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.context.EventContext;
import cn.dlmyl.crab4j.handler.ExceptionHandler;

/**
 * 简单的异常处理器
 *
 * @author dlmyL
 */
public class SimpleExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(Throwable cause, EventContext context) {
        System.out.println("====================================");
        System.out.printf("error message: %s %n", cause.getCause().getMessage());
        System.out.println("====================================");
        System.out.printf("busName: %s %n", context.getSource());
        System.out.printf("eventMessage: %s %n", context.getEvent());
        System.out.printf("className: %s %n", context.getSubscriber().getClass());
        System.out.printf("methodName: %s %n", context.getSubscribe().getName());
        System.out.println("====================================");
    }

}
