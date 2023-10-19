package cn.dlmyl.crab4j.core.event.internal;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 事件注册中心
 *
 * @author dlmyL
 */
public class Registry {

    public void bind(Object subscriber) {
        // 获取订阅方法列表
        List<Method> subscribeMethods = EventManager.getSubscribeMethods(subscriber);
        // 获取含有@Subscribe注释的方法进行缓存
        subscribeMethods.forEach(m -> EventManager.tierSubscriber(subscriber, m));
    }

    public void unbind(Object subscriber) {
        // 遍历注册表，修改订阅者的状态
        EventRepository.getRegistryMap().forEach((key, queue) ->
                queue.forEach(s -> {
                    if (s.getSubscribeObject() == subscriber) {
                    s.setDisable(true);
                }
            }
        ));
    }

    public ConcurrentLinkedQueue<Subscriber> lookup(String topic) {
        // 从注册表中获取topic所对应的订阅者集合
        return EventRepository.queryByTopic(topic);
    }

}
