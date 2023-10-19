package cn.dlmyl.crab4j.core.event;

import cn.dlmyl.crab4j.core.common.constant.Constants;
import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.event.internal.Dispatcher;
import cn.dlmyl.crab4j.core.event.internal.Registry;
import cn.dlmyl.crab4j.core.exception.IExceptionHandler;

import java.util.concurrent.Executor;

/**
 * 事件总线同步调度，同步也就意味着是有序执行
 *
 * @author dlmyL
 */
public class EventBus implements Bus {

    private final String busName;

    private final Registry registry;
    private final Dispatcher dispatcher;

    public EventBus() {
        this(Constants.DEFAULT_BUS_NAME, Dispatcher.SEQUENCE_EXECUTOR, null);
    }

    public EventBus(String busName) {
        this(busName, Dispatcher.SEQUENCE_EXECUTOR, null);
    }

    public EventBus(IExceptionHandler exceptionHandler) {
        this(Constants.DEFAULT_BUS_NAME, Dispatcher.SEQUENCE_EXECUTOR, exceptionHandler);
    }

    public EventBus(String busName, Executor executor, IExceptionHandler exceptionHandler) {
        this.busName = busName;
        this.dispatcher = Dispatcher.create(executor, exceptionHandler);
        this.registry = new Registry();
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
    public void post(Event event) {
        this.post(event, Constants.DEFAULT_TOPIC);
    }

    @Override
    public void post(Event event, String topic) {
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
