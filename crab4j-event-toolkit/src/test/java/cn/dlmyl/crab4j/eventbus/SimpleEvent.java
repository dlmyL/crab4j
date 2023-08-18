package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.eventbus.convention.Event;

/**
 * 简单的事件
 *
 * @author dlmyL
 */
public class SimpleEvent implements Event {

    private final String message;

    public SimpleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SimpleEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
