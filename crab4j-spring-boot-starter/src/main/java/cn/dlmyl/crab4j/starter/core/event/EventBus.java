package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.executor.DefaultExecutor;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.handler.ExceptionHandlerFactory;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * 事件总线
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
@SuppressWarnings("all")
public class EventBus {

    private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

    @Autowired
    private EventManager eventManager;

    /**
     * 事件的同步发送
     *
     * @param event {@link Event}
     */
    public void post(Event event) {
        EventListener eventListener = null;
        try {
            // 选取处理该事件的监听者类
            eventListener = eventManager.getEventListener(event.getClass()).get(0);
            // 发送事件
            eventListener.onMessage(event);
        } catch (Exception exception) {
            // 异常处理
            handleException(eventListener, exception);
        }
    }

    /**
     * 事件的异步发送
     *
     * @param event {@link Event}
     */
    public void asyncPost(Event event) {
        eventManager.getEventListener(event.getClass()).stream().map(listener -> {
            try {
                // 能否获取到监听者类的线程池执行器
                if (null != listener.getExecutor()) {
                    // 获取到就使用该执行器去异步执行事件处理
                    listener.getExecutor().execute(() -> listener.onMessage(event));
                } else {
                    // 获取不到就使用默认的线程池执行器去执行
                    DefaultExecutor.ASYNC_EXECUTOR.execute(() -> listener.onMessage(event));
                }
            } catch (Exception exception) {
                // 异常处理
                handleException(listener, exception);
            }
            return true;
        }).collect(Collectors.toList());
    }

    /**
     * 异常处理
     *
     * @param listener {@link EventListener}
     * @param exception {@link Exception}
     */
    private void handleException(EventListener listener, Exception exception) {
        logger.error(exception.getMessage(), exception);
        ExceptionHandlerFactory.provideExceptionHandler().handler(listener, exception);
    }

}
