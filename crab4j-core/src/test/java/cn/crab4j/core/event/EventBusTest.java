package cn.crab4j.core.event;

import cn.crab4j.core.eventbus.EventBus;
import cn.crab4j.core.eventbus.SimpleEventHub;
import cn.crab4j.core.eventbus.TopicProvider;
import cn.crab4j.core.eventbus.convention.Bus;
import cn.crab4j.core.handler.LoggingHandler;
import org.junit.Test;

/**
 * 简单的测试
 *
 * @author dlmyL
 */
public class EventBusTest {

    @Test
    public void test_EventBus() {
        SimpleEventHub.register(new SimpleListener());
        SimpleEventHub.post(new SimpleEvent("Uzi 来全杀了"));
        SimpleEventHub.post(new IntegerEvent(6379));
        SimpleEventHub.close();
    }

    @Test
    public void test_EventBusUseTopic() {
        SimpleEventHub.register(new SimpleListener());
        SimpleEventHub.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        SimpleEventHub.post(new SimpleEvent("天神下凡"), TopicProvider.of("my-topic"));

        SimpleEventHub.close();
    }

    @Test
    public void test_EventBusHaveEx1() {
        Bus bus = new EventBus(new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        bus.close();
    }

    @Test
    public void test_EventBusHaveEx2() {
        Bus bus = new EventBus(new LoggingHandler());
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        bus.close();
    }

}
