package cn.crab4j.example.framework.listener;

import cn.crab4j.example.framework.event.SimpleAsyncEvent;
import cn.crab4j.example.framework.executor.SimpleExecutor;
import cn.crab4j.starter.core.event.Subscribe;
import cn.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * SimpleAsyncListener
 *
 * @author dlmyL
 */
@Subscribe
public class SimpleAsyncListener implements EventListener<SimpleAsyncEvent> {

    @Override
    public ExecutorService getExecutor() {
        return SimpleExecutor.THREAD_POOL_EXECUTOR;
    }

    @Override
    public void onMessage(SimpleAsyncEvent event) {

        System.out.println("SimpleAsyncListener#onMessage 接收到异步事件：" + event.getMessage());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("SimpleAsyncListener#onMessage 处理异步事件完成");
    }

}
