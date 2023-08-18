package cn.dlmyl.crab4j.context;

import cn.dlmyl.crab4j.eventbus.Subscriber;

import java.lang.reflect.Method;

/**
 * 事件上下文默认实现
 *
 * @author dlmyL
 */
public class SubscriberEventContext implements EventContext {

    private final String busName;
    private final Subscriber subscriber;
    private final Object event;

    public SubscriberEventContext(String busName, Subscriber subscriber, Object event) {
        this.busName = busName;
        this.subscriber = subscriber;
        this.event = event;
    }

    @Override
    public String getSource() {
        return this.busName;
    }

    @Override
    public Object getSubscriber() {
        return subscriber != null? subscriber.getSubscribeObject() : null;
    }

    @Override
    public Method getSubscribe() {
        return subscriber != null? subscriber.getSubscribeMethod() : null;
    }

    @Override
    public Object getEvent() {
        return this.event;
    }

}
