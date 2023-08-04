package com.crab4j.core.common.thread;

import com.crab4j.core.common.builder.Builder;

import java.util.concurrent.*;

/**
 * ExecutorBuilder
 *
 * @author dlmyL
 */
public class ExecutorBuilder implements Builder<ThreadPoolExecutor> {

    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_QUEUE_CAPACITY = 1024;

    private int corePoolSize;

    private int maxPoolSize = Integer.MAX_VALUE;

    private long keepAliveTime = TimeUnit.SECONDS.toNanos(60);

    private BlockingQueue<Runnable> workQueue;

    private Boolean allowCoreThreadTimeOut;

    public ExecutorBuilder setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public ExecutorBuilder setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    public ExecutorBuilder setKeepAliveTime(long keepAliveTime, TimeUnit unit) {
        return setKeepAliveTime(unit.toNanos(keepAliveTime));
    }

    public ExecutorBuilder setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    public ExecutorBuilder setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    public ExecutorBuilder useArrayBlockingQueue(int capacity) {
        return setWorkQueue(new ArrayBlockingQueue<>(capacity));
    }

    public ExecutorBuilder useSynchronousQueue() {
        return useSynchronousQueue(false);
    }

    public ExecutorBuilder useSynchronousQueue(boolean fair) {
        return setWorkQueue(new SynchronousQueue<>(fair));
    }

    public ExecutorBuilder setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    public static ExecutorBuilder create() {
        return new ExecutorBuilder();
    }

    @Override
    public ThreadPoolExecutor build() {
        return build(this);
    }

    private static ThreadPoolExecutor build(ExecutorBuilder builder) {
        int corePoolSize = builder.corePoolSize;
        int maxPoolSize = builder.maxPoolSize;
        long keepAliveTime = builder.keepAliveTime;
        BlockingQueue<Runnable> workQueue;
        if (null != builder.workQueue) {
            workQueue = builder.workQueue;
        } else {
            workQueue = (corePoolSize <= 0) ? new SynchronousQueue<>() :
                    new LinkedBlockingQueue<>(DEFAULT_QUEUE_CAPACITY);
        }
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime, TimeUnit.NANOSECONDS,
                workQueue,
                threadFactory
        );
        if (null != builder.allowCoreThreadTimeOut) {
            threadPoolExecutor.allowCoreThreadTimeOut(builder.allowCoreThreadTimeOut);
        }
        return threadPoolExecutor;
    }

}
