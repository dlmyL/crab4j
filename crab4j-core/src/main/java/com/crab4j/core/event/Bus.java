package com.crab4j.core.event;

/**
 * Bus.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event, String topic);

    String getBusName();

    void close();

}
