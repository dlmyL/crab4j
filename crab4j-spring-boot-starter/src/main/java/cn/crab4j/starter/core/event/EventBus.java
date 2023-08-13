package cn.crab4j.starter.core.event;

import cn.crab4j.starter.core.executor.DefaultExecutor;
import cn.crab4j.starter.core.listener.EventListener;

import java.util.stream.Collectors;

/**
 * 事件总线
 *
 * @author dlmyL
 */
@SuppressWarnings("all")
public class EventBus {

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
        Class<?> clazz = listener.getClass();
        // TODO 处理异常
    }

}
