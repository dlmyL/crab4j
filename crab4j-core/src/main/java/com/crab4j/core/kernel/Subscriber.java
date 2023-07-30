package com.crab4j.core.kernel;

import java.lang.reflect.Method;

/**
 * 订阅者
 * <p>
 * Subscriber 是特定对象 Event 的订阅方法，用于执行被分发的事件。
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class Subscriber {

    /**
     * 订阅状态标识（true 解绑；false 绑定，默认为绑定）
     */
    private boolean disable = false;

    /**
     * 订阅的对象
     */
    private final Object subscribeObject;

    /**
     * 订阅的方法
     */
    private final Method subscribeMethod;

    public Subscriber(Object subscribeObject, Method subscribeMethod) {
        this.subscribeObject = subscribeObject;
        this.subscribeMethod = subscribeMethod;
    }

    public Object getSubscribeObject() {
        return subscribeObject;
    }

    public Method getSubscribeMethod() {
        return subscribeMethod;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

}
