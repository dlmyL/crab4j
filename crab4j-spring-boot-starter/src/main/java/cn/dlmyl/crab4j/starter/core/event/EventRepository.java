package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件仓库
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 2.0
 */
@SuppressWarnings("rawtypes")
public class EventRepository {

    /**
     * 事件注册表
     */
    private static final HashMap<Class, List<EventListener>> EVENT_LISTENER_REPOSITORY = new HashMap<>();

    /**
     * 事件响应表
     */
    private static final Map<Class, Class> EVENT_RESPONSE_REPOSITORY = new HashMap<>();

    public Map<Class, Class> getResponseRepository() {
        return EVENT_RESPONSE_REPOSITORY;
    }

    public List<EventListener> get(Class<? extends Event> eventClazz) {
        return EVENT_LISTENER_REPOSITORY.get(eventClazz);
    }

    public void put(Class<? extends Event> eventClazz, List<EventListener> eventListeners) {
        EVENT_LISTENER_REPOSITORY.put(eventClazz, eventListeners);
    }

}
