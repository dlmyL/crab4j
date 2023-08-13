package cn.crab4j.starter.config;

import cn.crab4j.starter.core.event.EventBus;
import cn.crab4j.starter.core.event.EventManager;
import cn.crab4j.starter.core.event.EventRegister;
import cn.crab4j.starter.core.init.Crab4JBootstrap;
import cn.crab4j.starter.toolkit.EventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Crab4J 自动配置类
 *
 * @author dlmyL
 */
@Configuration
@ComponentScan(value = {"cn.crab4j.starter"})
public class Crab4JAutoConfiguration {

    @Bean
    public EventManager eventManager() {
        return new EventManager();
    }

    @Bean
    public EventRegister eventRegister(EventManager eventManager) {
        return new EventRegister(eventManager);
    }

    @Bean(initMethod = "init")
    public Crab4JBootstrap bootstrap(EventRegister eventRegister) {
        return new Crab4JBootstrap(eventRegister);
    }

    @Bean
    public EventBus eventBus(EventManager eventManager) {
        return new EventBus(eventManager);
    }

    @Bean
    public EventPublisher eventPublisher(EventBus eventBus) {
        return new EventPublisher(eventBus);
    }

}
