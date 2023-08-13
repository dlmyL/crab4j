package cn.crab4j.starter.core.event;

import cn.crab4j.starter.core.listener.EventListener;

import java.lang.reflect.Method;

/**
 * 事件注册器
 *
 * @author dlmyL
 */
@SuppressWarnings("all")
public class EventRegister {

    public final static String EXE_METHOD = "onMessage";

    private final EventManager eventManager;

    public EventRegister(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    private Class<? extends Event> getEventFromExecutor(Class<?> eventExecutorClz) {
        Method[] methods = eventExecutorClz.getDeclaredMethods();
        for (Method method : methods) {
            if (isExecuteMethod(method)) {
                return checkAndGetEventParamType(method);
            }
        }
        throw new RuntimeException("Event param in " + eventExecutorClz + " " + EXE_METHOD
                + "() is not detected");
    }

    public void doRegistration(EventListener eventListener) {
        Class<? extends Event> eventClazz = getEventFromExecutor(eventListener.getClass());
        eventManager.register(eventClazz, eventListener);
    }

    private boolean isExecuteMethod(Method method) {
        return EXE_METHOD.equals(method.getName()) && !method.isBridge();
    }

    private Class checkAndGetEventParamType(Method method) {
        Class<?>[] exeParams = method.getParameterTypes();
        if (exeParams.length == 0) {
            throw new RuntimeException("Execute method in " + method.getDeclaringClass() + " should at least have one" +
                    " parameter");
        }
        if (!Event.class.isAssignableFrom(exeParams[0])) {
            throw new RuntimeException("Execute method in " + method.getDeclaringClass() + " should be the subClass " +
                    "of Event");
        }
        return exeParams[0];
    }

}
