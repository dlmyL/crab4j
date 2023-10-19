package cn.dlmyl.crab4j.core.event.internal;

import cn.dlmyl.crab4j.core.context.IEventContext;
import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.exception.IExceptionHandler;
import cn.dlmyl.crab4j.core.executor.SequenceExecutor;
import cn.dlmyl.crab4j.core.route.ExecutorRouteEnum;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 事件分发器
 *
 * @author dlmyL
 */
public class Dispatcher {

    public static final Executor SEQUENCE_EXECUTOR = SequenceExecutor.getInstance();

    private final Executor executor;

    private final IExceptionHandler exceptionHandler;

    private final IEventContext eventContext;

    public Dispatcher(Executor executor, IExceptionHandler exceptionHandler) {
        this.executor = executor;
        this.exceptionHandler = IExceptionHandler.DEFAULT;
        this.eventContext = IEventContext.DEFAULT;
    }

    public void dispatch(Bus bus, Registry registry, Event event, String topic) {
        ConcurrentLinkedQueue<Subscriber> subscribers = registry.lookup(topic);
        if (null == subscribers) {
            exceptionHandler.handle(
                    new IllegalArgumentException("The topic " + topic + " not bind yet"),
                    eventContext.of(bus.getBusName(), null, event)
            );
            return;
        }
        subscribers.stream()
                .filter(subscriber -> !subscriber.isDisable())
                .filter(subscriber -> {
                    Method subscribeMethod = subscriber.getSubscribeMethod();
                    Class<?> aClass = subscribeMethod.getParameterTypes()[0];
                    return aClass.isAssignableFrom(event.getClass());
                })
                .forEach(subscriber -> realInvokeSubscribe(subscriber, event, bus));
    }

    private void realInvokeSubscribe(Subscriber subscriber, Event event, Bus bus) {
        ExecutorRouteEnum executorRoute = ExecutorRouteEnum.match(event.getRoute(), null);
        executorRoute.getRouter().route(subscriber, event, bus);
    }

    public void close() {
        if (executor instanceof ExecutorService) {
            ((ExecutorService) executor).shutdown();
        }
    }

    public static Dispatcher create(Executor executor, IExceptionHandler exceptionHandler) {
        return new Dispatcher(executor, exceptionHandler);
    }

}