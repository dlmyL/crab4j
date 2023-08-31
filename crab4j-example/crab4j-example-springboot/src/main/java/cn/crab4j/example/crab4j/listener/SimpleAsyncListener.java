package cn.crab4j.example.crab4j.listener;

import cn.crab4j.example.crab4j.event.SimpleAsyncEvent;
import cn.crab4j.example.crab4j.executor.SimpleExecutor;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的异步事件监听类
 *
 * @author dlmyL
 */
@Sub
public class SimpleAsyncListener implements EventListener<SimpleAsyncEvent> {

    @Override
    public ExecutorService getExecutor() {
        // 使用了自定义的线程池执行器
        return SimpleExecutor.THREAD_POOL_EXECUTOR;
    }

    @Override
    public void onMessage(SimpleAsyncEvent event) {

        System.out.println("SimpleAsyncListener#onMessage - Async event consumer：" + event.getMessage());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("SimpleAsyncListener#onMessage - Async event consumer：completed!");
    }

}
