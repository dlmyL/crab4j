package com.crab4j.core.event.executor;

import java.util.concurrent.Executor;

/**
 * Executor.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public final class PerThreadExecutor implements Executor {

    private final static PerThreadExecutor INSTANCE = new PerThreadExecutor();

    public static PerThreadExecutor getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }

}
