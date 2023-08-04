package cn.crab4j.core.event;

import cn.crab4j.core.eventbus.convention.Event;

/**
 * IntegerEvent
 *
 * @author dlmyL
 */
public class IntegerEvent implements Event {

    private final Integer count;

    public IntegerEvent(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "IntegerEvent{" +
                "count=" + count +
                '}';
    }

}
