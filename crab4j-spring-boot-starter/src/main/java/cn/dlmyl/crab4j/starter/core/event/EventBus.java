package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.executor.DefaultExecutor;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.ExceptionHandlerFactory;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;

import java.util.stream.Collectors;

/**
 * 事件总线
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
@SuppressWarnings("all")
public class EventBus {

    private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

    private final EventManager eventManager;

    public EventBus(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public void post(Event event) {
        EventListener eventListener = null;
        try {
            eventListener = eventManager.getEventListener(event.getClass()).get(0);
            eventListener.onMessage(event);
        } catch (Exception exception) {
            handleException(eventListener, exception);
        }
    }

    public void asyncPost(Event event) {
        eventManager.getEventListener(event.getClass()).stream().map(listener -> {
            try {
                if (null != listener.getExecutor()) {
                    listener.getExecutor().execute(() -> listener.onMessage(event));
                } else {
                    DefaultExecutor.ASYNC_EXECUTOR.execute(() -> listener.onMessage(event));
                }
            } catch (Exception exception) {
                handleException(listener, exception);
            }
            return true;
        }).collect(Collectors.toList());
    }

    private void handleException(EventListener listener, Exception exception) {
        logger.error(exception.getMessage(), exception);
        ExceptionHandlerFactory.provideExceptionHandler().handler(listener, exception);
    }

}
