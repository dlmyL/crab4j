package cn.crab4j.example.crab4j.listener;

import cn.crab4j.example.crab4j.event.SimpleAsyncEvent;
import cn.dlmyl.crab4j.starter.core.event.Response;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的异步事件监听类
 *
 * @author dlmyL
 */
@Sub
public class SimpleAsyncListener implements EventListener<Response, SimpleAsyncEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Override
    public ExecutorService getExecutor() {
        return new ThreadPoolExecutor(
                5,
                10,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    @Override
    public Response onMessage(SimpleAsyncEvent event) {
        LOGGER.info("SimpleAsyncListener#onMessage - Async event consumer：%s", event.getMessage());
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("SimpleAsyncListener#onMessage - Async event consumer：completed!");
        return Response.buildSuccess();
    }

}
