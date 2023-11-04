package cn.dlmyl.crab4j.helper;

import cn.dlmyl.crab4j.executor.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadHelper
 *
 * @author dlmyL
 */
public class ThreadHelper {

    /**
     * 获得一个新的线程池，默认的策略如下：
     * <pre>
     *    1. 初始线程数为 0
     *    2. 最大线程数为Integer.MAX_VALUE
     *    3. 使用SynchronousQueue
     *    4. 任务直接提交给线程而不保持它们
     * </pre>
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService newExecutor() {
        return ExecutorBuilder.create().useSynchronousQueue().build();
    }

    /**
     * 新建一个线程池，默认的策略如下：
     * <pre>
     *    1. 初始线程数为corePoolSize指定的大小
     *    2. 没有最大线程数限制
     *    3. 默认使用LinkedBlockingQueue，默认队列大小为1024
     * </pre>
     *
     * @param corePoolSize 同时执行的线程数大小
     * @return @return {@link ExecutorService}
     */
    public static ExecutorService newExecutor(int corePoolSize) {
        ExecutorBuilder builder = ExecutorBuilder.create();
        if (corePoolSize > 0) {
            builder.setCorePoolSize(corePoolSize);
        }
        return builder.build();
    }

    /**
     * 获得一个新的线程池<br>
     * 如果maximumPoolSize &gt;= corePoolSize，在没有新任务加入的情况下，多出的线程将最多保留60s
     *
     * @param corePoolSize    初始线程池大小
     * @param maximumPoolSize 最大线程池大小
     * @return {@link ThreadPoolExecutor}
     */
    public static ThreadPoolExecutor newExecutor(int corePoolSize, int maximumPoolSize) {
        return ExecutorBuilder.create()
                .setCorePoolSize(corePoolSize)
                .setMaxPoolSize(maximumPoolSize)
                .build();
    }

    /**
     * 获得一个新的线程池，并指定最大任务队列大小<br>
     * 如果maximumPoolSize &gt;= corePoolSize，在没有新任务加入的情况下，多出的线程将最多保留60s
     *
     * @param corePoolSize     初始线程池大小
     * @param maximumPoolSize  最大线程池大小
     * @param maximumQueueSize 最大任务队列大小
     * @return {@link ThreadPoolExecutor}
     */
    public static ExecutorService newExecutor(int corePoolSize, int maximumPoolSize, int maximumQueueSize) {
        return ExecutorBuilder.create()
                .setCorePoolSize(corePoolSize)
                .setMaxPoolSize(maximumPoolSize)
                .setWorkQueue(new LinkedBlockingQueue<>(maximumQueueSize))
                .build();
    }

}
