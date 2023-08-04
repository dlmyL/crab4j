package cn.crab4j.core.eventbus;

import cn.crab4j.core.common.constant.Constants;
import cn.crab4j.core.common.thread.ThreadHelper;
import cn.crab4j.core.handler.ExceptionHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Async Event Bus.
 *
 * @author dlmyL
 */
public class AsyncEventBus extends EventBus {

    private static final ThreadPoolExecutor DEFAULT_EXECUTOR =
            ThreadHelper.newExecutor(Runtime.getRuntime().availableProcessors() + 1,
                    Runtime.getRuntime().availableProcessors() * 2);

    public AsyncEventBus(String busName, ThreadPoolExecutor executor,
                         ExceptionHandler exceptionHandler) {
        super(busName, executor, exceptionHandler);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        this(busName, executor, null);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, executor, null);
    }

    public AsyncEventBus() {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, DEFAULT_EXECUTOR, null);
    }

    public AsyncEventBus(ThreadPoolExecutor executor, ExceptionHandler exceptionHandler) {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, executor, exceptionHandler);
    }

}
