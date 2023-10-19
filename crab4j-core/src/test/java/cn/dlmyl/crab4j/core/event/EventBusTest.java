package cn.dlmyl.crab4j.core.event;

import org.junit.After;
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

    @After
    public void close() {
        eventBus.close();
    }

    @Test
    public void test_EventBus() {
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("Uzi 来全杀了"));
    }

    @Test
    public void test_EventBusUseTopic() {
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        eventBus.post(new SimpleEvent("天神下凡"), "my-topic");
    }

    @Test
    public void test_EventBusHaveEx1() {
        eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), "ex-topic");
    }

}
