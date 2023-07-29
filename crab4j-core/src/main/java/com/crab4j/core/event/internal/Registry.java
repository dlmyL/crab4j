package com.crab4j.core.event.internal;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Registry
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface Registry {

    void bind(Object subscriber);

    void unbind(Object subscriber);

    ConcurrentLinkedQueue<Subscriber> scan(String topic);

}
