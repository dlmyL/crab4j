package cn.dlmyl.crab4j.core.handler.impl;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.event.internal.Subscriber;
import cn.dlmyl.crab4j.core.exception.IExceptionHandler;
import cn.dlmyl.crab4j.core.handler.IEventHandler;

import java.lang.reflect.Method;

/**
 * SimpleEventHandler
 *
 * @author dlmyL
 */
public class SimpleEventHandler implements IEventHandler {

    private Subscriber subscriber;
    private Event event;
    private Bus bus;

    private IExceptionHandler exceptionHandler;
    private IEventContext eventContext;

    public SimpleEventHandler() {
    }

    public SimpleEventHandler(Subscriber subscriber, Event event, Bus bus) {
        this.subscriber = subscriber;
        this.event = event;
        this.bus = bus;
        this.exceptionHandler = IExceptionHandler.DEFAULT;
        this.eventContext = IEventContext.DEFAULT;
    }

    @Override
    public IEventHandler of(Subscriber subscriber, Event event, Bus bus) {
        return new SimpleEventHandler(subscriber, event, bus);
    }

    @Override
    public void execute() {
        Object subscribeObject = subscriber.getSubscribeObject();
        Method subscribeMethod = subscriber.getSubscribeMethod();
        try {
            subscribeMethod.invoke(subscribeObject, event);
        } catch (Exception e) {
            exceptionHandler.handle(e, eventContext.of(bus.getBusName(), subscriber, event));
        }
    }

}
