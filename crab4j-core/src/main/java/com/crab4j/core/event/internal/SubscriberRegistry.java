package com.crab4j.core.event.internal;

import com.crab4j.core.annotation.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 订阅者事件注册中心
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public class SubscriberRegistry implements Registry {

    /**
     * 注册表
     */
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>>
            subscriberContainer = new ConcurrentHashMap<>();

    @Override
    public void bind(Object subscriber) {
        // 获取订阅方法列表
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        // 获取含有@Subscribe注释的方法进行缓存
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    @Override
    public void unbind(Object subscriber) {
        // 遍历注册表，修改订阅者的状态
        subscriberContainer.forEach((key, queue) ->
                queue.forEach(s -> {
                    if (s.getSubscribeObject() == subscriber) {
                    s.setDisable(true);
                }
            }
        ));
    }

    @Override
    public ConcurrentLinkedQueue<Subscriber> scan(String topic) {
        // 从注册表中获取topic所对应的订阅者集合
        return subscriberContainer.get(topic);
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(Subscribe.class)
                            && m.getParameterCount() == 1
                            && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tierSubscriber(Object subscriber, Method method) {
        Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        subscriberContainer.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        subscriberContainer.get(topic).add(new Subscriber(subscriber, method));
    }

}
