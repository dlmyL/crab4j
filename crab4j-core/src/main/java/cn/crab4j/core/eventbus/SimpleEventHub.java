package cn.crab4j.core.eventbus;

import cn.crab4j.core.eventbus.convention.Event;
import cn.crab4j.core.eventbus.convention.Topic;

/**
 * SimpleEventHub
 *
 * @author dlmyL
 */
public class SimpleEventHub {

    private static final EventBus EVENT_BUS = new EventBus();

    private SimpleEventHub() {

    }

    public static EventBus getInstance() {
        return EVENT_BUS;
    }

    public static void register(Object obj) {
        EVENT_BUS.register(obj);
    }

    public static void unregister(Object obj) {
        EVENT_BUS.unregister(obj);
    }

    public static void post(Event event) {
        EVENT_BUS.post(event);
    }

    public static void post(Event event, Topic topic) {
        EVENT_BUS.post(event, topic);
    }

    public static void close() {
        EVENT_BUS.close();
    }

}
