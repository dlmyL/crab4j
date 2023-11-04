package cn.crab4j.example;

import cn.dlmyl.crab4j.annotation.Sub;

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
                "==> The event [%s] received and will take a action by ==> test2 %n", event.toString());
    }


    @Sub(topic = "ex-topic")
    public void test3(SimpleEvent event) {
        throw new RuntimeException("训练时长两年半的个人练习生代码报错");
    }

    @Sub
    public void test4(SimpleEvent event) {
        System.out.printf(Thread.currentThread().getName() +
                "==> The event [%s] received and will take a action by ==> test4 %n", event.toString());
    }

}
