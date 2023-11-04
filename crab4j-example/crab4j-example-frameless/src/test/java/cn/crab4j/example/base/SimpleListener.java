package cn.crab4j.example.base;

import cn.dlmyl.crab4j.annotation.Sub;

import java.util.concurrent.TimeUnit;

/**
 * 简单的监听器
 *
 * @author dlmyL
 */
public class SimpleListener  {

    @Sub
    public void test1(SimpleEvent event) {
        System.out.printf(Thread.currentThread().getName() +
                        "==> The event [%s] received and will take a action by ==> test1 %n", event.toString());
    }

    @Sub(topic = "my-topic")
    public void test2(SimpleEvent event) {
        System.out.printf(Thread.currentThread().getName() +
                "==> The event [%s] received and will take a action by ==> test2 %n", event);
    }

    @Sub
    public void test3(IntegerEvent event) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf(Thread.currentThread().getName() +
                "==> The event [%s] received and will take a action by ==> test3 %n", event.toString());
    }

    @Sub(topic = "ex-topic")
    public void test4(SimpleEvent event) {
        throw new RuntimeException(Thread.currentThread().getName() +
                "==> 训练时长两年半的个人练习生代码报错");
    }

    @Sub(topic = "async-topic")
    public void test5(IntegerEvent event) {
        System.out.printf(Thread.currentThread().getName() +
                "==> The event [%s] received and will take a action by ==> test5 %n", event.toString());
    }

    @Sub
    public void test6(SimpleEvent event) {
        System.out.printf(Thread.currentThread().getName() +
                "==> The event [%s] received and will take a action by ==> test6 %n", event.toString());
    }

}
