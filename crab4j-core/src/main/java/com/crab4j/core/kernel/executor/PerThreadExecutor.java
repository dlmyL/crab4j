package com.crab4j.core.kernel.executor;

import com.crab4j.core.common.thread.ThreadHelper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * PerThreadExecutor.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public final class PerThreadExecutor implements Executor {

    private final static PerThreadExecutor INSTANCE = new PerThreadExecutor();

    public static PerThreadExecutor getInstance() {
        return INSTANCE;
    }

    private final ExecutorService executor =
            ThreadHelper.newExecutor(5, 10, 1024);

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
        //executor.execute(command);
    }

}
