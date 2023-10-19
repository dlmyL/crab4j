package cn.dlmyl.crab4j.core.event.internal;

import cn.dlmyl.crab4j.core.annotation.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 事件管理
 *
 * @author dlmyL
 */
public class EventManager {

    public static List<Method> getSubscribeMethods(Object subscriber) {
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

    public static void tierSubscriber(Object subscriber, Method method) {
        Subscribe subscribe = method.getDeclaredAnnotation(Subscribe.class);
        String topic = subscribe.topic();
        EventRepository.getRegistryMap().computeIfAbsent(topic, key -> new ConcurrentLinkedQueue<>());
        EventRepository.queryByTopic(topic).add(new Subscriber(subscriber, method));
    }

}
