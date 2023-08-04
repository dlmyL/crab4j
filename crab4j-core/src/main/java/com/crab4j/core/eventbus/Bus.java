package com.crab4j.core.eventbus;

/**
 * Bus.
 *
 * @author dlmyL
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event, String topic);

    String getBusName();

    void close();

}
