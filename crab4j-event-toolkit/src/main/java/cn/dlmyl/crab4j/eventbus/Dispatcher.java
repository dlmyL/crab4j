package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.context.SubscriberEventContext;
import cn.dlmyl.crab4j.eventbus.convention.Bus;
import cn.dlmyl.crab4j.eventbus.convention.Event;
import cn.dlmyl.crab4j.eventbus.convention.Topic;
import cn.dlmyl.crab4j.executor.PerThreadExecutor;
import cn.dlmyl.crab4j.executor.SequenceExecutor;
import cn.dlmyl.crab4j.handler.ExceptionHandler;

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

    public static final Executor PER_THREAD_EXECUTOR = PerThreadExecutor.getInstance();
    public static final Executor SEQUENCE_EXECUTOR = SequenceExecutor.getInstance();

    private final Executor executor;
    private final ExceptionHandler exceptionHandler;

    public Dispatcher(Executor executor, ExceptionHandler exceptionHandler) {
        this.executor = executor;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Bus bus, Registry registry, Event event, Topic topic) {
        ConcurrentLinkedQueue<Subscriber> subscribers = registry.scan(topic.getTopic());
        if (null == subscribers) {
            if (exceptionHandler != null) {
                exceptionHandler.handle(
                        new IllegalArgumentException("The topic " + topic + " not bind yet"),
                        new SubscriberEventContext(bus.getBusName(), null, event)
                );
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

    private void realInvokeSubscribe(Subscriber subscriber, Object event, Bus bus) {
        Method subscribeMethod = subscriber.getSubscribeMethod();
        Object subscribeObject = subscriber.getSubscribeObject();
        executor.execute(() -> {
            try {
                subscribeMethod.invoke(subscribeObject, event);
            } catch (Exception e) {
                if (null != exceptionHandler) {
                    exceptionHandler.handle(e, new SubscriberEventContext(bus.getBusName(), subscriber, event));
                }
            }
        });
    }

    public void close() {
        if (executor instanceof ExecutorService) {
            ((ExecutorService) executor).shutdown();
        }
    }

    public static Dispatcher create(Executor executor, ExceptionHandler exceptionHandler) {
        return new Dispatcher(executor, exceptionHandler);
    }

}