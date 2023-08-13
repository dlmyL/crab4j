package cn.crab4j.starter.toolkit;

import cn.crab4j.starter.core.event.Event;
import cn.crab4j.starter.core.event.EventBus;

/**
 * 事件发布器
 *
 * @author dlmyL
 */
public class EventPublisher {

    private final EventBus eventBus;

    public EventPublisher(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void publish(Event event) {
        this.preCheck();
        this.eventBus.post(event);
    }

    public void asyncPublish(Event event) {
        this.preCheck();
        this.eventBus.asyncPost(event);
    }

    private void preCheck() {
        if (this.eventBus == null) {
            throw new RuntimeException("请在启动类上使用 @EnableCrab4J 注解");
        }
    }

}