package cn.dlmyl.crab4j.core.event.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * EventRepository
 *
 * @author dlmyL
 */
public class EventRepository {

    /**
     * 注册表
     */
    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> REGISTRY_MAP = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, ConcurrentLinkedQueue<Subscriber>> getRegistryMap() {
        return REGISTRY_MAP;
    }

    public static ConcurrentLinkedQueue<Subscriber> queryByTopic(String topic) {
        return REGISTRY_MAP.get(topic);
    }

}
