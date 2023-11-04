package cn.dlmyl.crab4j.executor;

import cn.dlmyl.crab4j.helper.ThreadHelper;

import java.util.concurrent.Executor;

/**
 * BasicExecutor
 *
 * @author dlmyL
 */
public enum BasicExecutor implements Executor {

    /**
     * The singleton instance
     */
    INSTANCE;

    private static final Executor DEFAULT_THREAD_POOL = ThreadHelper.newExecutor(8, 16, 1024);

    @Override
    public void execute(Runnable command) {
        DEFAULT_THREAD_POOL.execute(command);
    }

}
