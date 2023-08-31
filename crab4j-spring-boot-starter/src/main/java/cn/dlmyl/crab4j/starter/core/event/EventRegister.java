package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * 事件注册中心
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
@SuppressWarnings("all")
public class EventRegister {

    private final static String EXE_METHOD = "onMessage";

    @Autowired
    private EventManager eventManager;

    /**
     * 注册事件
     *
     * @param eventListener {@link EventListener}
     */
    public void doRegistration(EventListener eventListener) {
        Class<? extends Event> eventClazz = getEventFromExecutor(eventListener.getClass());
        eventManager.register(eventClazz, eventListener);
    }

    private Class<? extends Event> getEventFromExecutor(Class<?> eventExecutorClazz) {
        Method[] methods = eventExecutorClazz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)) {
                return checkAndGetEventParamType(method);
            }
        }
        throw new Crab4JException("Event param in " + eventExecutorClazz + " " + EXE_METHOD
                + "() is not detected");
    }

    private boolean isExecuteMethod(Method method) {
        return EXE_METHOD.equals(method.getName()) && !method.isBridge();
    }

    private Class checkAndGetEventParamType(Method method) {
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0) {
            throw new Crab4JException("Execute method in " + method.getDeclaringClass() + " should at least have one" +
                    " parameter");
        }
        if (!Event.class.isAssignableFrom(exeParams[0])) {
            throw new Crab4JException("Execute method in " + method.getDeclaringClass() + " should be the subClass " +
                    "of Event");
        }
        return exeParams[0];
    }

}
