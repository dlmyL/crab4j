package cn.crab4j.example.framework.event;

import cn.crab4j.starter.core.event.Event;

/**
 * SimpleAsyncEvent
 *
 * @author dlmyL
 */
public class SimpleAsyncEvent implements Event {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SimpleAsyncEvent(String message) {
        this.message = message;
    }

    public static class Builder {

        private String message;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public SimpleAsyncEvent build() {
            return new SimpleAsyncEvent(message);
        }

    }

    @Override
    public String toString() {
        return "SimpleAsyncEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
