package cn.crab4j.example.framework.listener;

import cn.crab4j.example.framework.event.SimpleAsyncEvent;
import cn.crab4j.example.framework.executor.SimpleExecutor;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * SimpleAsyncListener
 *
 * @author dlmyL
 */
@Sub
public class SimpleAsyncListener implements EventListener<SimpleAsyncEvent> {

    @Override
    public ExecutorService getExecutor() {
        return SimpleExecutor.THREAD_POOL_EXECUTOR;
    }

    @Override
    public void onMessage(SimpleAsyncEvent event) {

        System.out.println("异步事件消费者：" + event.getMessage());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("异步事件消费者：completed");
    }

}
