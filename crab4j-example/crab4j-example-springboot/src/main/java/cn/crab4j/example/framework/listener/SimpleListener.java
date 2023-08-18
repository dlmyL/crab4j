package cn.crab4j.example.framework.listener;

import cn.crab4j.example.framework.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.core.event.Subscribe;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.TimeUnit;

/**
 * SimpleHandler
 *
 * @author dlmyL
 */
@Subscribe
public class SimpleListener implements EventListener<SimpleEvent> {

    @Override
    public void onMessage(SimpleEvent simpleEvent) {

        System.out.println("SimpleListener#onMessage 接收到同步事件：" + simpleEvent.getMessage());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("SimpleListener#onMessage 处理同步事件完成");
    }

}
