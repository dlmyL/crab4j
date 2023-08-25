package cn.dlmyl.crab4j.starter;

import cn.dlmyl.crab4j.starter.core.event.Event;
import cn.dlmyl.crab4j.starter.core.event.EventBus;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;

/**
 * 事件发布器
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public class EventPublisher {

    private final EventBus eventBus;

    public EventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(Event event) {
        this.preCheck(event);
        this.eventBus.post(event);
    }

    public void asyncPublish(Event event) {
        this.preCheck(event);
        this.eventBus.asyncPost(event);
    }

    private void preCheck(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("this argument is required, it must not be null.");
        }
        if (this.eventBus == null) {
            throw new Crab4JException("Please use the @EnableCrab4J annotation on the startup class.");
        }
    }

}