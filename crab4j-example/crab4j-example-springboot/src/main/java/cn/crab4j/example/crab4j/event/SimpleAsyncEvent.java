package cn.crab4j.example.crab4j.event;

import cn.dlmyl.crab4j.starter.core.event.Event;

/**
 * 一个简单的异步事件
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
