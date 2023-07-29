package com.crab4j.core.event.internal;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 事件注册抽象
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface Registry {

    /**
     * 订阅者绑定
     *
     * @param subscriber 订阅者
     */
    void bind(Object subscriber);

    /**
     * 订阅者解绑
     *
     * @param subscriber 订阅者
     */
    void unbind(Object subscriber);

    /**
     * 扫描注册表中topic对应的订阅者
     *
     * @param topic topic
     * @return topic的订阅者集合
     */
    ConcurrentLinkedQueue<Subscriber> scan(String topic);

}
