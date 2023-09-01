package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件管理器
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class EventManager {

    @Autowired
    private EventRepository eventRepository;

    /**
     * 事件注册
     *
     * @param eventClazz 事件类
     * @param executor   事件监听执行器
     */
    public void register(Class<? extends Event> eventClazz, EventListener executor) {
        List<EventListener> eventListeners = new ArrayList<>();
        eventListeners.add(executor);
        eventRepository.put(eventClazz, eventListeners);
    }

    /**
     * 获取事件监听集合
     *
     * @param eventClazz 事件类
     * @return 事件监听集合
     */
    public List<EventListener> getEventListener(Class<? extends Event> eventClazz) {
        List<EventListener> eventListeners = scanListener(eventClazz);
        if (eventListeners == null || eventListeners.size() == 0) {
            throw new Crab4JException(eventClazz + "is not registered in eventManage, please register first");
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
        List<EventListener> eventListenerList = null;
        eventListenerList = eventRepository.get(eventClazz);
        return eventListenerList;
    }

    public Class getResponseRepository(EventListener handler) {
        return eventRepository.getResponseRepository().get(handler.getClass());
    }

}