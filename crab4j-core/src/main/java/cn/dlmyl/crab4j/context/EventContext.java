package cn.dlmyl.crab4j.context;

import cn.dlmyl.crab4j.core.Subscriber;

import java.lang.reflect.Method;

/**
 * 存储事件的上下文
 *
 * @author dlmyL
 */
public class EventContext {

    private final String busName;
    private final Subscriber subscriber;
    private final Object event;

    public EventContext(Builder builder) {
        this.busName = builder.busName;
        this.subscriber = builder.subscriber;
        this.event = builder.event;
    }

    /**
     * 获取事件源
     *
     * @return 事件源
     */
    public String getSource() {
        return this.busName;
    }

    /**
     * 获取订阅对象
     *
     * @return 订阅对象
     */
    public Object getSubscriber() {
        return this.subscriber != null? subscriber.getSubscribeObject() : null;
    }

    /**
     * 获取订阅方法
     *
     * @return {@link Method}
     */
    public Method getSubscribe() {
        return this.subscriber != null? subscriber.getSubscribeMethod() : null;
    }

    /**
     * 获取事件
     *
     * @return 事件
     */
    public Object getEvent() {
        return this.event;
    }

    public static class Builder {

        private String busName;
        private Subscriber subscriber;
        private Object event;

        public Builder busName(String busName) {
            this.busName = busName;
            return this;
        }

        public Builder subscriber(Subscriber subscriber) {
            this.subscriber = subscriber;
            return this;
        }

        public Builder event(Object event) {
            this.event = event;
            return this;
        }

        public EventContext build() {
            return new EventContext(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "busName='" + busName + '\'' +
                    ", subscriber=" + subscriber +
                    ", event=" + event +
                    '}';
        }
    }

}
