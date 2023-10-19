package cn.crab4j.example;

import cn.crab4j.example.crab4j.event.SimpleAsyncEvent;
import cn.crab4j.example.crab4j.event.SimpleEvent;
import cn.crab4j.example.crab4j.listener.SimpleListener;
import cn.dlmyl.crab4j.starter.Pub;
import cn.dlmyl.crab4j.starter.annotation.EnableCrab4J;
import cn.dlmyl.crab4j.starter.core.event.Response;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExampleApplication
 *
 * @author dlmyL
 */
@EnableCrab4J
@RestController
@SpringBootApplication
public class ExampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ExampleApplication.class)
                .initializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
                    // 在程序运行前向上下文中注入bean
                    ctx.registerBean("crab4j", Pub.class, Pub.X);
                })
                .run(args);
    }

    @GetMapping("/eventResp")
    public String eventResp() {
        // 发布有返回值的同步事件（不推荐）
        Response response = Pub.X.eventResp(new SimpleEvent.Builder().message("Uzi 来全杀了").build());
        if (response.isSuccess()) {
            LOGGER.info("有返回值的同步事件执行成功");
        }
        LOGGER.info("=== 主线程 ===：我在这里干等着");
        return "OK";
    }

    @GetMapping("/event")
    public String event() {
        // 发布没有返回值的同步事件（不推荐）
        Pub.X.event(new SimpleEvent.Builder().message("Uzi 来全杀了").build(), true);
        LOGGER.info("=== 主线程 ===：我在这里干等着");
        return "OK";
    }

    @GetMapping("/async-event")
    public String asyncEvent() {
        // 发布异步事件（推荐使用）
        Pub.X.event(new SimpleAsyncEvent.Builder().message("中下野辅，别坑我 Shy 哥").build());
        LOGGER.info("=== 主线程 ===：我可以干其他事情");
        return "OK";
    }


    // TIPS: 发布混合事件时，一定要把异步事件放在前面

    @GetMapping("/mixed-event")
    public String mixedEvent() {
        // 发布异步事件
        Pub.X.event(new SimpleAsyncEvent.Builder().message("我下路怎么做事").build());
        LOGGER.info("=== 主线程 ===：我可以干其他事情");

        // 发布同步事件
        Pub.X.event(new SimpleEvent.Builder().message("质疑、理解、成为、超越").build(), true);
        LOGGER.info("=== 主线程 ===：我在这里干等着");
        return "OK";
    }

}
