package cn.crab4j.example.framework.listener;

import cn.crab4j.example.framework.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.TimeUnit;

/**
 * SimpleHandler
 *
 * @author dlmyL
 */
@Sub
public class SimpleListener implements EventListener<SimpleEvent> {

    @Override
    public void onMessage(SimpleEvent simpleEvent) {

        System.out.println("同步事件消费者：" + simpleEvent.getMessage());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("同步事件消费者：completed");
    }

}
