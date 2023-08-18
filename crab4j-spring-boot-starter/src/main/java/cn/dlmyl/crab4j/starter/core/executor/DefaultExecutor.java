package cn.dlmyl.crab4j.starter.core.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 默认执行器
 *
 * @author dlmyL
 */
public class DefaultExecutor {

    /**
     * 如果处理器无定制线程池，则使用该线程池
     */
    public static final ExecutorService ASYNC_EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() * 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

}
