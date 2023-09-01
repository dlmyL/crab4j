package cn.dlmyl.crab4j.starter.config;

import cn.dlmyl.crab4j.starter.Pub;
import cn.dlmyl.crab4j.starter.core.event.EventBus;
import cn.dlmyl.crab4j.starter.core.event.EventManager;
import cn.dlmyl.crab4j.starter.core.event.EventRegister;
import cn.dlmyl.crab4j.starter.core.event.EventRepository;
import cn.dlmyl.crab4j.starter.core.init.Crab4JBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Crab4J 自动配置类
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
@Configuration
@ComponentScan(value = {"cn.dlmyl.crab4j.starter"})
public class Crab4JAutoConfiguration {

    @Bean
    public EventManager eventManager() {
        return new EventManager();
    }

    @Bean
    public EventRepository eventRepository() {
        return new EventRepository();
    }

    @Bean
    public EventRegister eventRegister() {
        return new EventRegister();
    }

    @Bean(initMethod = "init")
    public Crab4JBootstrap bootstrap() {
        return new Crab4JBootstrap();
    }

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }

    @Bean
    public Pub pub() {
        return Pub.X;
    }

}
