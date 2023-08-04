package cn.crab4j.core.eventbus;

import cn.crab4j.core.common.utils.AssertUtils;
import cn.crab4j.core.eventbus.convention.Topic;

/**
 * DefaultTopic
 *
 * @author dlmyL
 */
public class DefaultTopic implements Topic {

    private final String topic;

    public DefaultTopic(String topic) {
        AssertUtils.notNull(topic, "Topic must not be null");
        this.topic = topic;
    }

    public static DefaultTopic of(String pattern) {
        return new DefaultTopic(pattern);
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
