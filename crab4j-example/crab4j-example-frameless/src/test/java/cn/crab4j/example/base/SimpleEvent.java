package cn.crab4j.example.base;

import cn.dlmyl.crab4j.core.Event;

/**
 * SimpleEvent
 *
 * @author dlmyL
 */
public class SimpleEvent implements Event {

    private String message;

    public String getMessage() {
        return message;
    }

    public SimpleEvent(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "SimpleEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
