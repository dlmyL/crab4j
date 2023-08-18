package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件管理器
 *
 * @author dlmyL
 */
@SuppressWarnings("rawtypes")
public class EventManager {

    /**
     * 事件仓库
     */
    private static final ConcurrentHashMap<Class, List<EventListener>> EVENT_REPOSITORY = new ConcurrentHashMap<>();

    /**
     * 事件注册
     *
     * @param eventClazz 事件类
     * @param executor   事件监听执行器
     */
    public void register(Class<? extends Event> eventClazz, EventListener executor) {
        List<EventListener> eventListeners = new ArrayList<>();
        eventListeners.add(executor);
        EVENT_REPOSITORY.put(eventClazz, eventListeners);
    }

    /**
     * 获取事件监听集合
     *
     * @param eventClazz 事件类
     * @return 时间监听集合
     */
    public List<EventListener> getEventListener(Class<? extends Event> eventClazz) {
        List<EventListener> eventListeners = scanListener(eventClazz);
        if (eventListeners == null || eventListeners.size() == 0) {
            throw new Crab4JException(eventClazz + "is not registered in eventHub, please register first");
        }
        return eventListeners;
    }

    /**
     * 扫描事件监听类
     *
     * @param eventClazz 事件类
     * @return 监听事件的类
     */
    private List<EventListener> scanListener(Class<? extends Event> eventClazz) {
        List<EventListener> eventListeners = null;
        eventListeners = EVENT_REPOSITORY.get(eventClazz);
        return eventListeners;
    }

}