package cn.dlmyl.crab4j.core;

import cn.dlmyl.crab4j.constant.Constants;
import cn.dlmyl.crab4j.context.EventContext;
import cn.dlmyl.crab4j.exception.ExceptionHandler;
import cn.dlmyl.crab4j.exception.FatalExceptionHandler;
import cn.dlmyl.crab4j.executor.BasicExecutor;
import cn.dlmyl.crab4j.logger.Logger;
import cn.dlmyl.crab4j.logger.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * 事件总线同步调度，同步也就意味着是有序执行
 *
 * @author dlmyL
 */
public class EventBus implements Bus {

    private static final Logger log = LoggerFactory.getLogger(EventBus.class);

    private final String busName;
    private final Registry registry;
    private final Dispatcher dispatcher;

    private static final ExceptionHandler<EventContext> DEFAULT_EXCEPTION_HANDLER = new FatalExceptionHandler();

    public EventBus() {
        this(Constants.DEFAULT_BUS_NAME, BasicExecutor.INSTANCE, DEFAULT_EXCEPTION_HANDLER);
    }

    public EventBus(String busName) {
        this(busName, BasicExecutor.INSTANCE, DEFAULT_EXCEPTION_HANDLER);
    }

    public EventBus(Executor executor) {
        this(Constants.DEFAULT_BUS_NAME, executor, DEFAULT_EXCEPTION_HANDLER);
    }

    public EventBus(ExceptionHandler<EventContext> exceptionHandler) {
        this(Constants.DEFAULT_BUS_NAME, BasicExecutor.INSTANCE, exceptionHandler);
    }

    public EventBus(String busName, Executor executor) {
        this(busName, executor, new FatalExceptionHandler());
    }

    public EventBus(Executor executor, ExceptionHandler<EventContext> exceptionHandler) {
        this(Constants.DEFAULT_BUS_NAME, executor, exceptionHandler);
    }

    public EventBus(String busName, ExceptionHandler<EventContext> exceptionHandler) {
        this(busName, BasicExecutor.INSTANCE, exceptionHandler);
    }

    public EventBus(String busName, Executor executor, ExceptionHandler<EventContext> exceptionHandler) {
        this.busName = busName;
        this.dispatcher =  new DispatcherFactory(executor, exceptionHandler).newInstance();
        this.registry = new RegistryFactory().newInstance();
        log.info("EventBus Construction completed!");
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


    private static final class DispatcherFactory implements EventFactory<Dispatcher> {

        private final Executor executor;
        private final ExceptionHandler<EventContext> exceptionHandler;

        public DispatcherFactory(Executor executor, ExceptionHandler<EventContext> exceptionHandler) {
            this.executor = executor;
            this.exceptionHandler = exceptionHandler;
        }

        @Override
        public Dispatcher newInstance() {
            return new Dispatcher(executor, exceptionHandler);
        }
    }

    private static final class RegistryFactory implements EventFactory<Registry> {

        @Override
        public Registry newInstance() {
            return new Registry();
        }
    }

}
