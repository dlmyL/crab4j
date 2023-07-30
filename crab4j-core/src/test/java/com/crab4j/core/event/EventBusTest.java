package com.crab4j.core.event;

import org.junit.Test;

/**
 * 简单的测试
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class EventBusTest {

    @Test
    public void test_EventBus() {
        Bus bus = new EventBus();
        bus.register(new SimpleListener());
        bus.post("Uzi 来全杀了");
        for (int i = 0; i <= 4; i++) {
            bus.post(new SimpleEvent("杀疯了"));
        }
        bus.post(8848);
    }

    @Test
    public void test_EventBusUseTopic() {
        Bus bus = new EventBus();
        bus.register(new SimpleListener());
        for (int i = 0; i < 10; i++) {
            bus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        }
        bus.post("天神下凡", "my-topic");
    }

    @Test
    public void test_EventBusHaveEx() {
        Bus bus = new EventBus(new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        //bus.post("坤你太美");
        bus.post("教练，我想打篮球", "ex-topic");
        //bus.post(1024);
    }

}
