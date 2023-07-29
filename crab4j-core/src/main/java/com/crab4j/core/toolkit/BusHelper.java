package com.crab4j.core.toolkit;

import com.crab4j.core.event.AsyncEventBus;
import com.crab4j.core.event.Event;
import com.crab4j.core.event.EventBus;

/**
 * 总线工具包，方便操作
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class BusHelper {

    private EventBus eventBus;
    private AsyncEventBus asyncEventBus;

    public BusHelper(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public BusHelper(AsyncEventBus asyncEventBus) {
        this.asyncEventBus = asyncEventBus;
    }

    public void post(Event event) {
        eventBus.post(event);
    }

    public void post(Event event, String topic) {
        eventBus.post(event, topic);
    }

    public void asyncPost(Event event) {
        asyncEventBus.post(event);
    }

    public void asyncPost(Event event, String topic) {
        asyncEventBus.post(event, topic);
    }

}
