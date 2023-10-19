package cn.dlmyl.crab4j.core.context.impl;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.event.internal.Subscriber;

import java.lang.reflect.Method;

/**
 * 事件上下文默认实现
 *
 * @author dlmyL
 */
public class SimpleEventContext implements IEventContext {

    public SimpleEventContext() {
    }

    private String busName;
    private Subscriber subscriber;
    private Object event;

    public SimpleEventContext(String busName, Subscriber subscriber, Object event) {
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

    @Override
    public IEventContext of(String busName, Subscriber subscriber, Object event) {
        return new SimpleEventContext(busName, subscriber, event);
    }

}
