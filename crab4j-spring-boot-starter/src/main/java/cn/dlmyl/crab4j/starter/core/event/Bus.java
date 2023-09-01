package cn.dlmyl.crab4j.starter.core.event;

/**
 * Bus
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 2.0
 */
public interface Bus {

    Response post(Event event);

    void asyncPost(Event event);

}
