package cn.dlmyl.crab4j.common.thread;

import java.util.concurrent.ExecutorService;

/**
 * 全局公共线程池
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public class GlobalThreadPool {

    private static ExecutorService executor;

    private GlobalThreadPool() {
    }

    static {
        init();
    }

    /**
     * 初始化全局线程池
     */
    public static synchronized void init() {
        if (null != executor) {
            executor.shutdown();
        }
        // 通过构建者模式创建全局线程池
        executor = ExecutorBuilder.create().useSynchronousQueue().build();
    }

    /**
     * 关闭公共线程池
     *
     * @param isNow 是否立即关闭而不等待正在执行的线程
     */
    public static synchronized void shutdown(boolean isNow) {
        if (null != executor) {
            if (isNow) {
                executor.shutdownNow();
            } else {
                executor.shutdown();
            }
        }
    }

    /**
     * 获得 {@link ExecutorService}
     *
     * @return {@link ExecutorService}
     */
    public static ExecutorService getExecutor() {
        return executor;
    }

    /**
     * 直接在公共线程池中执行线程
     *
     * @param runnable 可运行对象
     */
    public static void execute(Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            throw new RuntimeException("Exception when running task!");
        }
    }

}
