package cn.dlmyl.crab4j.core.handler;

import cn.dlmyl.crab4j.core.event.basic.Bus;
import cn.dlmyl.crab4j.core.event.basic.Event;
import cn.dlmyl.crab4j.core.event.internal.Subscriber;
import cn.dlmyl.crab4j.core.spi.ServiceLoaders;

/**
 * IEventHandler
 *
 * @author dlmyL
 */
public interface IEventHandler {

    IEventHandler DEFAULT = ServiceLoaders.loadDefault(IEventHandler.class);

    IEventHandler of(Subscriber subscriber, Event event, Bus bus);

    void execute();

}
