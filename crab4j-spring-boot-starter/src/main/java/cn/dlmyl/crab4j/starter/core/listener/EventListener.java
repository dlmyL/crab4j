package cn.dlmyl.crab4j.starter.core.listener;

import cn.dlmyl.crab4j.starter.core.event.Event;
import cn.dlmyl.crab4j.starter.core.event.Response;

import java.util.concurrent.ExecutorService;

/**
 * 事件监听接口
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 2.0
 */
public interface EventListener<R extends Response, E extends Event> {

    /**
     * 获取执行器
     *
     * @return {@link ExecutorService}
     */
    default ExecutorService getExecutor() {
        return null;
    }

    /**
     * 消费事件消息
     *
     * @param event 事件类
     */
    R onMessage(E event);

}
