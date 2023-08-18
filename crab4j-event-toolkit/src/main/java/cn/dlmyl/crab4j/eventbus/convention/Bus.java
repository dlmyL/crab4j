package cn.dlmyl.crab4j.eventbus.convention;

/**
 * Bus.
 *
 * @author dlmyL
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Event event);

    void post(Event event, Topic topic);

    void close();

    String getBusName();

}
