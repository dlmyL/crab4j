package cn.crab4j.example.base;

import cn.dlmyl.crab4j.core.EventBus;
import org.junit.Test;

/**
 * EventBusTest
 *
 * @author dlmyL
 */
public class EventBusTest {

    @Test
    public void test_EventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());

        for (int i = 0; i < 500; i++) {
            //eventBus.post(new SimpleEvent("Uzi 来全杀了"));
            //eventBus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
            eventBus.post(new SimpleEvent("天神下凡"), "my-topic");
        }

        //eventBus.post(new SimpleEvent("Uzi 来全杀了"));
        //eventBus.post(new IntegerEvent(6379));

        System.out.println("EventBus 测试结束");

        try {
            Thread.sleep(30 * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        eventBus.close();
    }

    @Test
    public void test_EventBusUseTopic() {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        eventBus.post(new SimpleEvent("天神下凡"), "my-topic");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        eventBus.close();
    }

    @Test
    public void test_EventBusHaveEx1() {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), "ex-topic");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        eventBus.close();
    }

    @Test
    public void test_EventBusHaveEx2() {
        EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), "ex-topic");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        eventBus.close();
    }

}
