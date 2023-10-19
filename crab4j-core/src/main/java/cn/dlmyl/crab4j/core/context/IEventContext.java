package cn.dlmyl.crab4j.core.context;

import cn.dlmyl.crab4j.core.event.internal.Subscriber;
import cn.dlmyl.crab4j.core.spi.ServiceLoaders;

import java.lang.reflect.Method;

/**
 * 存储事件的上下文
 *
 * @author dlmyL
 */
public interface IEventContext {

    IEventContext DEFAULT = ServiceLoaders.loadDefault(IEventContext.class);

    IEventContext of(String busName, Subscriber subscriber, Object event);

    /**
     * 获取事件源
     *
     * @return 事件源
     */
    String getSource();

    /**
     * 获取订阅对象
     *
     * @return 订阅对象
     */
    Object getSubscriber();

    /**
     * 获取订阅方法
     *
     * @return {@link Method}
     */
    Method getSubscribe();

    /**
     * 获取事件
     *
     * @return 事件
     */
    Object getEvent();

}
