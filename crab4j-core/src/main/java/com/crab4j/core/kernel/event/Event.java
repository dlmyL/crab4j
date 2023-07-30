package com.crab4j.core.kernel.event;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 事件抽象，提供事件执行序列
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public abstract class Event implements Serializable {

    private static final long serialVersionUID = -6566931481695385335L;

    private static final AtomicLong SEQUENCE = new AtomicLong(0);

    private final long sequence = SEQUENCE.getAndIncrement();

    public long sequence() {
        return sequence;
    }

}