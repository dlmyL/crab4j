package com.crab4j.core.common.concurrent;

import com.crab4j.core.common.extension.design.Builder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程工厂构建器
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class ThreadFactoryBuilder implements Builder<ThreadFactory> {

    private static final long serialVersionUID = -8222100000989066011L;

    /**
     * 线程前缀名
     */
    private String prefix;

    /**
     * 是否守护线程
     */
    private Boolean daemon;

    /**
     * 线程优先级
     */
    private Integer priority;

    /**
     * 线程未捕获异常处理器
     */
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    /**
     * 线程工厂
     */
    private ThreadFactory threadFactory;

    public ThreadFactoryBuilder threadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    public ThreadFactoryBuilder prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ThreadFactoryBuilder daemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder priority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format("Thread priority ({}) must be >= {}", priority,
                    Thread.MIN_PRIORITY));
        }
        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format("Thread priority ({}) must be <= {}", priority,
                    Thread.MAX_PRIORITY));
        }
        this.priority = priority;
        return this;
    }

    public void uncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public static ThreadFactoryBuilder builder() {
        return new ThreadFactoryBuilder();
    }

    @Override
    public ThreadFactory build() {
        ThreadFactoryBuilder builder = new ThreadFactoryBuilder();
        ThreadFactory backingThreadFactory = (null != builder.threadFactory) ? builder.threadFactory
                : Executors.defaultThreadFactory();
        String prefix = builder.prefix;
        Boolean daemon = builder.daemon;
        Integer priority = builder.priority;
        Thread.UncaughtExceptionHandler handler = builder.uncaughtExceptionHandler;
        AtomicLong count = (null == prefix) ? null : new AtomicLong();
        return r -> {
            Thread thread = backingThreadFactory.newThread(r);
            if (null != prefix) {
                thread.setName(prefix + "_" + count.getAndIncrement());
            }
            if (null != daemon) {
                thread.setDaemon(daemon);
            }
            if (null != priority) {
                thread.setPriority(priority);
            }
            if (null != handler) {
                thread.setUncaughtExceptionHandler(handler);
            }
            return thread;
        };
    }

}
