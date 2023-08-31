package cn.dlmyl.crab4j.starter;

import cn.dlmyl.crab4j.starter.core.event.Event;
import cn.dlmyl.crab4j.starter.core.event.EventBus;
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
    private EventBus eventBus;

    /**
     * 发布事件，默认是异步事件
     *
     * @param event 事件对象
     */
    public void event(Event event) {
        preCheck(event);
        eventBus.asyncPost(event);
    }

    /**
     * 发布事件，通过 needSync 参数控制是同步发送还是异步发送
     *
     * @param event 事件对象
     * @param needSync 控制发送是同步还是异步
     */
    public void event(Event event, boolean needSync) {
        preCheck(event);
        if (needSync) {
            eventBus.post(event);
        } else {
            eventBus.asyncPost(event);
        }
    }

    private void preCheck(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("this argument is required, it must not be null.");
        }
        if (eventBus == null) {
            throw new Crab4JException("Please use the @EnableCrab4J annotation on the startup class.");
        }
    }

}