package cn.crab4j.example.framework.event;

import cn.dlmyl.crab4j.starter.core.event.Event;

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

    public void setMessage(String message) {
        this.message = message;
    }

    public SimpleEvent(String message) {
        this.message = message;
    }

    public static class Builder {

        private String message;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public SimpleEvent build() {
            return new SimpleEvent(message);
        }

    }

    @Override
    public String toString() {
        return "SimpleEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
