package cn.dlmyl.crab4j.executor;

import cn.dlmyl.crab4j.common.thread.ThreadHelper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 来一个事件就有一个线程来执行
 *
 * @author dlmyL
 */
public final class PerThreadExecutor implements Executor {

    private PerThreadExecutor() {
    }

    private final static PerThreadExecutor INSTANCE = new PerThreadExecutor();

    public static PerThreadExecutor getInstance() {
        return INSTANCE;
    }

    private final ExecutorService executor =
            ThreadHelper.newExecutor(5, 10, 1024);

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }

}
