package cn.dlmyl.crab4j.exception;

import cn.dlmyl.crab4j.context.EventContext;
import cn.dlmyl.crab4j.eventbus.EventBus;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 默认的日志处理器
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public class DefaultExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(Throwable cause, EventContext context) {
        message(context);
        Logger logger = logger(context);
        if (logger.isLoggable(Level.INFO)) {
            logger.log(Level.INFO, message(context), cause);
        }
    }

    private static Logger logger(EventContext context) {
        return Logger.getLogger(EventBus.class.getName() + "." + context.getSource());
    }

    private static String message(EventContext context) {
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

