package cn.dlmyl.crab4j.core.route;

import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.event.internal.Subscriber;
import cn.dlmyl.crab4j.core.handler.IEventHandler;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

/**
 * 事件路由
 *
 * @author dlmyL
 */
public abstract class ExecutorRouter {

    protected static Logger logger = LoggerFactory.getLogger(ExecutorRouter.class);

    protected IEventHandler eventHandler;

    public ExecutorRouter() {
        eventHandler = IEventHandler.DEFAULT;
    }

    public abstract void route(Subscriber subscriber, Event event, Bus bus);

}