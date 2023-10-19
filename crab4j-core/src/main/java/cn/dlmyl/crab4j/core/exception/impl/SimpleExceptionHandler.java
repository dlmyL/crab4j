package cn.dlmyl.crab4j.core.exception.impl;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.event.EventBus;
import cn.dlmyl.crab4j.core.exception.IExceptionHandler;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 默认的日志处理器
 *
 * @author dlmyL
 */
public class SimpleExceptionHandler implements IExceptionHandler {

    @Override
    public void handle(Throwable cause, IEventContext context) {
        Logger logger = logger(context);
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, message(context), cause);
        }
    }

    private static Logger logger(IEventContext context) {
        return Logger.getLogger(EventBus.class.getName() + "." + context.getSource());
    }

    private static String message(IEventContext context) {
        Method method = context.getSubscribe();
        return "Exception thrown by subscriber method "
                + method.getName()
                + '('
                + method.getParameterTypes()[0].getName()
                + ')'
                + " on subscriber "
                + context.getSubscriber()
                + " when dispatching event: "
                + context.getEvent();
    }

}

