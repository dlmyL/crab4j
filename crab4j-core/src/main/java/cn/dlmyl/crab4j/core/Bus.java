package cn.dlmyl.crab4j.core;

/**
 * Bus.
 *
 * @author dlmyL
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Event event);

    void post(Event event, String topic);

    void close();

    String getBusName();

}
