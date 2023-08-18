package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.eventbus.convention.Event;

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
