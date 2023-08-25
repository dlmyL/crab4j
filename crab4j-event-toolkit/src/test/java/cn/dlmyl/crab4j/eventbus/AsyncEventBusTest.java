package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.common.thread.ThreadHelper;
import cn.dlmyl.crab4j.eventbus.convention.Bus;
import cn.dlmyl.crab4j.exception.DefaultExceptionHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单的异步测试
 *
 * @author dlmyL
 */
public class AsyncEventBusTest {

    private ThreadPoolExecutor executor;

    @Before
    public void init() {
        executor = ThreadHelper.newExecutor(Runtime.getRuntime().availableProcessors() + 1,
                Runtime.getRuntime().availableProcessors() * 2);
    }

    @Test
    public void test_AsyncEventBus() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor);
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("Uzi 来全杀了"));
        bus.post(new SimpleEvent("杀疯了"));

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusUseTopic() throws InterruptedException {
        Bus bus = new AsyncEventBus();
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        bus.post(new SimpleEvent("天神下凡"), TopicProvider.of("my-topic"));

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusHaveEx1() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor, new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("坤你太美"));
        bus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusHaveEx2() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor, new DefaultExceptionHandler());
        bus.register(new SimpleListener());
        bus.post(new SimpleEvent("坤你太美"));
        bus.post(new SimpleEvent("教练，我想打篮球"), TopicProvider.of("ex-topic"));

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

}
