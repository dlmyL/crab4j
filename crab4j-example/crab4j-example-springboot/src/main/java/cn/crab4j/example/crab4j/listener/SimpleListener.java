package cn.crab4j.example.crab4j.listener;

import cn.crab4j.example.crab4j.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.concurrent.TimeUnit;

/**
 * 一个简单的同步事件监听类
 *
 * @author dlmyL
 */
@Sub
public class SimpleListener implements EventListener<SimpleEvent> {

    @Override
    public void onMessage(SimpleEvent simpleEvent) {

        System.out.println("SimpleListener#onMessage - Sync event consumer：" + simpleEvent.getMessage());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("SimpleListener#onMessage - Sync event consumer：completed!");
    }

}
