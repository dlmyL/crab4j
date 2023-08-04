package com.crab4j.core.eventbus;

import com.crab4j.core.common.constant.Constants;
import com.crab4j.core.common.thread.ThreadHelper;
import com.crab4j.core.handler.ExceptionHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 事件总线异步调度，异步也就意味着是乱序执行
 *
 * @author dlmyL
 */
public class AsyncEventBus extends EventBus {

    private static final ThreadPoolExecutor DEFAULT_EXECUTOR =
            ThreadHelper.newExecutor(5, 10);

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
