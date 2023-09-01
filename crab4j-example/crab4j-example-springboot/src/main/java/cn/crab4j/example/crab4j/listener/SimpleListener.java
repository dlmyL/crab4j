package cn.crab4j.example.crab4j.listener;

import cn.crab4j.example.crab4j.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.core.event.Response;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 一个简单的同步事件监听类
 *
 * @author dlmyL
 */
@Sub
public class SimpleListener implements EventListener<Response, SimpleEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    @Override
    public Response onMessage(SimpleEvent event) {
        LOGGER.info("SimpleListener#onMessage - Sync event consumer：%s", event.getMessage());

        if (event.getMessage() == null) {
            LOGGER.error("%s Event content cannot be null", event.getClass().getSimpleName());
            return Response.buildFailure("Event content cannot be null");
        }

        // 模拟业务执行时间
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        LOGGER.info("SimpleListener#onMessage - Sync event consumer：completed!");

        return Response.buildSuccess();
    }

}
