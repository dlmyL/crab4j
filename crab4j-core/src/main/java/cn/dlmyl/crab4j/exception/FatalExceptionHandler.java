package cn.dlmyl.crab4j.exception;

import cn.dlmyl.crab4j.context.EventContext;
import cn.dlmyl.crab4j.logger.Logger;
import cn.dlmyl.crab4j.logger.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 默认的日志处理器
 *
 * @author dlmyL
 */
public class FatalExceptionHandler implements ExceptionHandler<EventContext> {

    private static final Logger log = LoggerFactory.getLogger(FatalExceptionHandler.class);

    @Override
    public void handle(Throwable cause, EventContext context) {
        log.warn("%s, %s", message(context), cause.toString());
    }

    @Override
    public String message(EventContext context) {
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

