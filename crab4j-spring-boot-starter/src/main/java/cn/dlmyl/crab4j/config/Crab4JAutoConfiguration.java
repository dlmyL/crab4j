package cn.dlmyl.crab4j.config;

import cn.dlmyl.crab4j.core.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Crab4J 自动装配
 *
 * @author dlmyL
 */
@Configuration
@ComponentScan(value = {"cn.dlmyl.crab4j"})
public class Crab4JAutoConfiguration {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

}
