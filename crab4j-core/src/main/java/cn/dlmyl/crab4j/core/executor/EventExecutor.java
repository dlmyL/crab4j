package cn.dlmyl.crab4j.core.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * EventExecutor
 *
 * @author dlmyL
 */
public interface EventExecutor extends Executor {

    /**
     * 获取执行器
     *
     * @return {@link ExecutorService}
     */
    default ExecutorService getExecutor() {
        return null;
    }

}
