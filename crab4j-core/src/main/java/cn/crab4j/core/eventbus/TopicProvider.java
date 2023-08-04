package cn.crab4j.core.eventbus;

import cn.crab4j.core.common.utils.AssertUtils;
import cn.crab4j.core.eventbus.convention.Topic;

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
