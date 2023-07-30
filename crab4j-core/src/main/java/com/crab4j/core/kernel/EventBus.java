package com.crab4j.core.kernel;

import com.crab4j.core.common.constant.Constants;
import com.crab4j.core.handler.ExceptionHandler;

import java.util.concurrent.Executor;

/**
 * 事件总线同步调度器
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class EventBus implements Bus {

    private final String busName;
    private final Dispatcher dispatcher;
    private final Registry registry = new Registry();

    public EventBus() {
        this(Constants.DEFAULT_BUS_NAME, Dispatcher.SEQUENCE_EXECUTOR, null);
    }

    public EventBus(String busName) {
        this(busName, Dispatcher.SEQUENCE_EXECUTOR, null);
    }

    public EventBus(ExceptionHandler exceptionHandler) {
        this(Constants.DEFAULT_BUS_NAME, Dispatcher.SEQUENCE_EXECUTOR, exceptionHandler);
    }

    public EventBus(String busName, Executor executor, ExceptionHandler exceptionHandler) {
        this.busName = busName;
        this.dispatcher = Dispatcher.create(executor, exceptionHandler);
    }

    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event, Constants.DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        this.dispatcher.dispatch(this, registry, event, topic);
    }

    @Override
    public String getBusName() {
        return this.busName;
    }

    @Override
    public void close() {
        this.dispatcher.close();
    }

}
