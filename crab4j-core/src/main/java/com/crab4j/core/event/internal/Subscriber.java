package com.crab4j.core.event.internal;

import java.lang.reflect.Method;

/**
 * Subscriber
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class Subscriber {

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
