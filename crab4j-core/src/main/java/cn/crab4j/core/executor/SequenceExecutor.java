package cn.crab4j.core.executor;

import java.util.concurrent.Executor;

/**
 * 顺序执行器
 *
 * @author dlmyL
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