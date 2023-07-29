package com.crab4j.core.context;

import java.lang.reflect.Method;

/**
 * 存储事件的上下文
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface EventContext {

    /**
     * 获取事件源
     *
     * @return 事件源
     */
    String getSource();

    /**
     * 获取订阅对象
     *
     * @return 订阅对象
     */
    Object getSubscriber();

    /**
     * 获取订阅方法
     *
     * @return {@link Method}
     */
    Method getSubscribe();

    /**
     * 获取事件
     *
     * @return 事件
     */
    Object getEvent();

}
