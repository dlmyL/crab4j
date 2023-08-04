package com.crab4j.core.event;

import com.crab4j.core.eventbus.Bus;
import com.crab4j.core.eventbus.EventBus;
import com.crab4j.core.eventbus.EventBusCenter;
import org.junit.Test;

/**
 * 简单的测试
 *
 * @author dlmyL
 */
public class EventBusTest {

    @Test
    public void test_EventBus() {
        EventBusCenter.register(new SimpleListener());
        EventBusCenter.post("Uzi 来全杀了");
        for (int i = 0; i <= 4; i++) {
            EventBusCenter.post(new SimpleEvent("杀疯了"));
        }
        EventBusCenter.post(8848);

        EventBusCenter.close();
    }

    @Test
    public void test_EventBusUseTopic() {
        EventBusCenter.register(new SimpleListener());
        for (int i = 0; i < 10; i++) {
            EventBusCenter.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        }
        EventBusCenter.post("天神下凡", "my-topic");

        EventBusCenter.close();
    }

    @Test
    public void test_EventBusHaveEx() {
        Bus bus = new EventBus(new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        //bus.post("坤你太美");
        bus.post("教练，我想打篮球", "ex-topic");
        //bus.post(1024);

        bus.close();
    }

}
