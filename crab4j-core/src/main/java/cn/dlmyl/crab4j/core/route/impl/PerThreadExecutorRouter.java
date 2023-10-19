package cn.dlmyl.crab4j.core.route.impl;

import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.event.internal.Subscriber;
import cn.dlmyl.crab4j.core.executor.PerThreadExecutor;
import cn.dlmyl.crab4j.core.route.ExecutorRouter;

/**
 * PerThreadStrategy
 *
 * @author dlmyL
 */
public class PerThreadExecutorRouter extends ExecutorRouter {

    @Override
    public void route(Subscriber subscriber, Event event, Bus bus) {
        PerThreadExecutor.getInstance().execute(
                () -> eventHandler.of(subscriber, event, bus).execute());
    }

}