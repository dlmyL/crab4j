package cn.dlmyl.crab4j.core.event;

import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.route.ExecutorRouteEnum;

/**
 * 简单的事件
 *
 * @author dlmyL
 */
public class SimpleEvent implements Event {

    @Override
    public String getRoute() {
        return ExecutorRouteEnum.PER_THREAD.getName();
    }

    private final String message;

    public SimpleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "SimpleEvent{" +
                "message='" + message + '\'' +
                '}';
    }

}
