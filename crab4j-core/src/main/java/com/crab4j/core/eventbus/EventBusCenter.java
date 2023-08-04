package com.crab4j.core.eventbus;

/**
 * EventBusCenter
 *
 * @author dlmyL
 */
public class EventBusCenter {

    private static final EventBus EVENT_BUS = new EventBus();

    private EventBusCenter() {

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

    public static void post(Object obj) {
        EVENT_BUS.post(obj);
    }

    public static void post(Object obj, String topic) {
        EVENT_BUS.post(obj, topic);
    }

    public static void close() {
        EVENT_BUS.close();
    }

}
