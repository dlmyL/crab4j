package com.crab4j.core.event;

/**
 * 简单的事件
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class SimpleEvent extends Event {

    private String message;

    private long eventSeq;

    public SimpleEvent(String message) {
        this.message = message;
        this.eventSeq = super.sequence();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getEventSeq() {
        return eventSeq;
    }

    public void setEventSeq(long eventSeq) {
        this.eventSeq = eventSeq;
    }

    @Override
    public String toString() {
        return "SimpleEvent{" +
                "message='" + message + '\'' +
                ", eventSeq=" + eventSeq +
                '}';
    }

}