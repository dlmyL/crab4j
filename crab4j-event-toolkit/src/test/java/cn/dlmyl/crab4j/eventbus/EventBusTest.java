package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.exception.DefaultExceptionHandler;
import org.junit.Before;
import org.junit.Test;

/**
 * 简单的测试
 *
 * @author dlmyL
 */
public class EventBusTest {

    private EventBus eventBus;

    @Before
    public void init() {
        eventBus = new EventBus();
    }

    @Test
    public void test_EventBus() {
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("Uzi 来全杀了"));
        eventBus.post(new IntegerEvent(6379));

        eventBus.close();
    }

    @Test
    public void test_EventBusUseTopic() {
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        eventBus.post(new SimpleEvent("天神下凡"), TopicProvider.of("my-topic"));

        eventBus.close();
    }

    @Test
    public void test_EventBusHaveEx1() {
        eventBus = new EventBus(new SimpleExceptionHandler());
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        eventBus.close();
    }

    @Test
    public void test_EventBusHaveEx2() {
        eventBus = new EventBus(new DefaultExceptionHandler());
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        eventBus.close();
    }

}
