package com.crab4j.core.kernel;

import com.crab4j.core.common.constant.Constants;
import com.crab4j.core.handler.ExceptionHandler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 事件总线同步调度器
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class AsyncEventBus extends EventBus {

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

    public AsyncEventBus(ThreadPoolExecutor executor, ExceptionHandler exceptionHandler) {
        this(Constants.DEFAULT_ASYNC_BUS_NAME, executor, exceptionHandler);
    }

}
