package com.crab4j.core.event;

import com.crab4j.core.common.thread.ThreadHelper;
import com.crab4j.core.handler.LoggingHandler;
import com.crab4j.core.kernel.AsyncEventBus;
import com.crab4j.core.kernel.Bus;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 简单的异步测试
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class AsyncEventBusTest {

    private ThreadPoolExecutor executor;

    @Before
    public void init() {
        executor = ThreadHelper.newExecutor(5, 10);
    }

    @Test
    public void test_AsyncEventBus() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor);
        bus.register(new SimpleListener());
        bus.post("Uzi 来全杀了");
        for (int i = 0; i <= 4; i++) {
            bus.post(new SimpleEvent("杀疯了"));
        }
        bus.post(8848);

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusUseTopic() throws InterruptedException {
        Bus bus = new AsyncEventBus();
        bus.register(new SimpleListener());
        for (int i = 0; i < 10; i++) {
            bus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
        }
        bus.post("天神下凡", "my-topic");

        bus.post(89757);

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusHaveEx1() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor, new SimpleExceptionHandler());
        bus.register(new SimpleListener());
        bus.post("坤你太美");
        bus.post("教练，我想打篮球", "ex-topic");
        bus.post(1024);

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

    @Test
    public void test_EventBusHaveEx2() throws InterruptedException {
        Bus bus = new AsyncEventBus(executor, new LoggingHandler());
        bus.register(new SimpleListener());
        bus.post("坤你太美");
        bus.post("教练，我想打篮球", "ex-topic");
        bus.post(1024);

        TimeUnit.SECONDS.sleep(8);
        bus.close();
    }

}
