package com.crab4j.core.event;

import com.crab4j.core.common.constant.Constants;
import com.crab4j.core.handler.ExceptionHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步事件总线
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class AsyncEventBus extends EventBus {

    public AsyncEventBus(String busName, ExceptionHandler exceptionHandler,
                         ThreadPoolExecutor executor) {
        super(busName, executor, exceptionHandler);
    }

    public AsyncEventBus(String busName, ThreadPoolExecutor executor) {
        this(busName, null, executor);
    }

    public AsyncEventBus(ThreadPoolExecutor executor) {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, null, executor);
    }

    public AsyncEventBus(ExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, exceptionHandler, executor);
    }

}
