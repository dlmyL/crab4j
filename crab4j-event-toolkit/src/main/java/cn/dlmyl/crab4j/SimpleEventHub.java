package cn.dlmyl.crab4j;

import cn.dlmyl.crab4j.eventbus.EventBus;
import cn.dlmyl.crab4j.eventbus.convention.Event;
import cn.dlmyl.crab4j.eventbus.convention.Topic;

/**
 * SimpleEventHub
 *
 * @author dlmyL
 */
public class SimpleEventHub {

    private static final EventBus EVENT_BUS = new EventBus();

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
