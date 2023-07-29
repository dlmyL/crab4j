package com.crab4j.core.event.executor;

import java.util.concurrent.Executor;

/**
 * Executor.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class SequenceExecutor implements Executor {

    private final static SequenceExecutor INSTANCE = new SequenceExecutor();

    public static SequenceExecutor getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

}
