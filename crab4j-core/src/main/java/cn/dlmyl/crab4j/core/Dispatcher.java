package cn.dlmyl.crab4j.core;

import cn.dlmyl.crab4j.context.EventContext;
import cn.dlmyl.crab4j.exception.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/**
 * 事件分发器
 *
 * @author dlmyL
 */
public class Dispatcher {

    private final Executor executor;
    private final ExceptionHandler<EventContext> exceptionHandler;

    public Dispatcher(Executor executor, ExceptionHandler<EventContext> exceptionHandler) {
        this.executor = executor;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Bus bus, Registry registry, Event event, String topic) {
        ConcurrentLinkedQueue<Subscriber> subscribers = registry.scan(topic);
        if (null == subscribers) {
            if (exceptionHandler != null) {
                exceptionHandlerWrapper(bus, event, topic);
            }
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
        Method subscribeMethod = subscriber.getSubscribeMethod();
        Object subscribeObject = subscriber.getSubscribeObject();
        executor.execute(() -> {
            try {
                subscribeMethod.invoke(subscribeObject, event);
            } catch (Exception e) {
                if (null != exceptionHandler) {
                    exceptionHandlerWrapper(e, subscriber, event, bus);
                }
            }
        });
    }


    // ====== exception handler wrapper ======

    private void exceptionHandlerWrapper(Bus bus, Event event, String topic) {
        exceptionHandler.handle(
                new IllegalArgumentException("The topic " + topic + " not bind yet"),
                new EventContext.Builder()
                        .busName(bus.getBusName())
                        .subscriber(null)
                        .event(event)
                        .build()
        );
    }

    private void exceptionHandlerWrapper(Exception e, Subscriber subscriber, Event event, Bus bus) {
        exceptionHandler.handle(e,
                new EventContext.Builder()
                        .busName(bus.getBusName())
                        .subscriber(subscriber)
                        .event(event)
                        .build()
        );
    }

}