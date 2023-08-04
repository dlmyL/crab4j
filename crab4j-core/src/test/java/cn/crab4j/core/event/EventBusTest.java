package cn.crab4j.core.event;

import cn.crab4j.core.eventbus.DefaultTopic;
import cn.crab4j.core.eventbus.convention.Bus;
import cn.crab4j.core.eventbus.EventBus;
import cn.crab4j.core.eventbus.EventBusCenter;
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
        EventBusCenter.post(new SimpleEvent("Uzi 来全杀了"));
        EventBusCenter.post(new IntegerEvent(6379));
        EventBusCenter.close();
    }

    @Test
    public void test_EventBusUseTopic() {
        EventBusCenter.register(new SimpleListener());
        EventBusCenter.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        EventBusCenter.post(new SimpleEvent("天神下凡"), DefaultTopic.of("my-topic"));

        EventBusCenter.close();
    }

    @Test
    public void test_EventBusHaveEx() {
        Bus bus = new EventBus(new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("教练，我想打篮球"), DefaultTopic.of("ex-topic"));

        bus.close();
    }

}
