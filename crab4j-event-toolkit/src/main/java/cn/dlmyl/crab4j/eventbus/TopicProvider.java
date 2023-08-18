package cn.dlmyl.crab4j.eventbus;

import cn.dlmyl.crab4j.common.utils.AssertUtils;
import cn.dlmyl.crab4j.eventbus.convention.Topic;

/**
 * TopicProvider
 *
 * @author dlmyL
 */
public class TopicProvider implements Topic {

    private final String topic;

    private TopicProvider(String topic) {
        AssertUtils.notNull(topic, "Topic must not be null");
        this.topic = topic;
    }

    public static TopicProvider of(String topic) {
        return new TopicProvider(topic);
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "DefaultTopic{" +
                "topic='" + topic + '\'' +
                '}';
    }

}
