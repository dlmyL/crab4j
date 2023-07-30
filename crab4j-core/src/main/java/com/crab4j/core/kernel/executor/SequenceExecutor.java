package com.crab4j.core.kernel.executor;

import java.util.concurrent.Executor;

/**
 * 顺序执行器
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class SequenceExecutor implements Executor {

    private SequenceExecutor() {
    }

    private static final SequenceExecutor INSTANCE = new SequenceExecutor();

    public static SequenceExecutor getInstance() {
        return INSTANCE;
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }

}