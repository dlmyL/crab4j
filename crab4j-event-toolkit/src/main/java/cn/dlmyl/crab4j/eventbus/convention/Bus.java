package cn.dlmyl.crab4j.eventbus.convention;

/**
 * Bus.
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Event event);

    void post(Event event, Topic topic);

    void close();

    String getBusName();

}
