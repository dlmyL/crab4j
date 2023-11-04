package cn.crab4j.example;

import cn.dlmyl.crab4j.core.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExampleApplication
 *
 * @author dlmyL
 */
@RestController
@SpringBootApplication
public class ExampleApplication implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @GetMapping("/event")
    public String event() {
        EventBus eventBus = applicationContext.getBean(EventBus.class);
        eventBus.register(new SimpleListener());
        for (int i = 0; i < 5; i++) {
            eventBus.post(new SimpleEvent("Uzi 来全杀了"));
            eventBus.post(new SimpleEvent("中下野辅，别坑我 Shy 哥"));
            eventBus.post(new SimpleEvent("天神下凡"), "my-topic");
        }
        LOGGER.info("=== 主线程 ===");
        return "OK";
    }

    @GetMapping("/event-ex")
    public String eventEx() {
        EventBus eventBus = applicationContext.getBean(EventBus.class);
        eventBus.register(new SimpleListener());
        eventBus.post(new SimpleEvent("教练，我想打篮球"), "ex-topic");
        LOGGER.info("=== 主线程 ===");
        return "OK";
    }


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
