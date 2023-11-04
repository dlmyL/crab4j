package cn.dlmyl.crab4j.core;

import cn.dlmyl.crab4j.annotation.Sub;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 事件注册中心
 *
 * @author dlmyL
 */
public class Registry {

    /**
     * 注册表
     */
    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> EVENT_REPOSITORY = new ConcurrentHashMap<>();


    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber, m));
    }

    public void unbind(Object subscriber) {
        EVENT_REPOSITORY.forEach((key, queue) ->
                queue.forEach(s -> {
                    if (s.getSubscribeObject() == subscriber) {
                    s.setDisable(true);
                }
            }
        ));
    }


    private List<Method> getSubscribeMethods(Object subscriber) {
        List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(m -> m.isAnnotationPresent(Sub.class)
                            && m.getParameterCount() == 1
                            && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tierSubscriber(Object subscriber, Method method) {
        Sub subscribe = method.getDeclaredAnnotation(Sub.class);
        String topic = subscribe.topic();
        EVENT_REPOSITORY.computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        EVENT_REPOSITORY.get(topic).add(new Subscriber(subscriber, method));
    }


    public ConcurrentLinkedQueue<Subscriber> scan(String topic) {
        return EVENT_REPOSITORY.get(topic);
    }

}
