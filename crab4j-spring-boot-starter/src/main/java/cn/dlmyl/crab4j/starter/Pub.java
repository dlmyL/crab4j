package cn.dlmyl.crab4j.starter;

import cn.dlmyl.crab4j.starter.core.event.Bus;
import cn.dlmyl.crab4j.starter.core.event.Event;
import cn.dlmyl.crab4j.starter.core.event.Response;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 事件发布器
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 2.0
 */
public enum Pub {

    /**
     * 单例
     */
    X;

    @Autowired
    private Bus bus;

    public Response eventResp(Event event) {
        preCheck(event);
        return bus.post(event);
    }

    public void event(Event event) {
        this.event(event, false);
    }

    public void event(Event event, boolean needSync) {
        preCheck(event);
        if (needSync) {
            bus.post(event);
        } else {
            bus.asyncPost(event);
        }
    }

    private void preCheck(Event event) {
        if (bus == null) {
            throw new Crab4JException("Please use the @EnableCrab4J annotation on the startup class.");
        }
        if (event == null) {
            throw new IllegalArgumentException("this argument is required, it must not be null.");
        }
    }

}