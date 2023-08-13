package cn.crab4j.starter.toolkit;

import cn.crab4j.starter.core.event.Event;
import cn.crab4j.starter.core.event.EventBus;
import cn.crab4j.starter.exception.Crab4JException;
import cn.crab4j.starter.logger.Logger;
import cn.crab4j.starter.logger.LoggerFactory;

/**
 * 事件发布器
 *
 * @author dlmyL
 */
public class EventPublisher {

    private Logger logger = LoggerFactory.getLogger(EventPublisher.class);

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
            logger.error("this argument is required, it must not be null.");
            throw new IllegalArgumentException("this argument is required, it must not be null.");
        }
        if (this.eventBus == null) {
            logger.error("Please use the @EnableCrab4J annotation on the startup class.");
            throw new Crab4JException("Please use the @EnableCrab4J annotation on the startup class.");
        }
    }

}