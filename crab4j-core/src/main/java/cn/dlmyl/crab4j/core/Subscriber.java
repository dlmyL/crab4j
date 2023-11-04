package cn.dlmyl.crab4j.core;

import java.lang.reflect.Method;

/**
 * 订阅者
 *
 * @author dlmyL
 */
public class Subscriber {

    /**
     * 订阅状态标识（true 解绑；false 绑定，默认为绑定）
     */
    private boolean disable = false;

    private final Object subscribeObject;

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
