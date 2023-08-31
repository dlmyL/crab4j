package cn.crab4j.example;

import cn.crab4j.example.crab4j.event.SimpleAsyncEvent;
import cn.crab4j.example.crab4j.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.Pub;
import cn.dlmyl.crab4j.starter.annotation.EnableCrab4J;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @GetMapping("/event")
    public String event() {
        // 发布同步事件（会阻塞主线程，一般不建议使用）
        Pub.X.event(new SimpleEvent.Builder().message("Uzi 来全杀了").build(), true);
        System.out.println("ExampleApplication#event 主线程：我在这里干等着");
        return "OK";
    }

    @GetMapping("/async-event")
    public String asyncEvent() {
        // 发布异步事件（推荐使用）
        Pub.X.event(new SimpleAsyncEvent.Builder().message("Shy 哥天神下凡").build());
        System.out.println("ExampleApplication#asyncEvent 主线程：我可以干其他事情");
        return "OK";
    }


    // TIPS: 发布混合事件时，一定要把异步事件放在前面

    @GetMapping("/mixed-event")
    public String mixedEvent() {
        // 发布异步事件（推荐使用）
        Pub.X.event(new SimpleAsyncEvent.Builder().message("Shy 哥天神下凡").build());
        System.out.println("ExampleApplication#mixedEvent 主线程：我可以干其他事情");

        // 发布同步事件（会阻塞主线程，一般不建议使用）
        Pub.X.event(new SimpleEvent.Builder().message("Uzi 来全杀了").build(), true);
        System.out.println("ExampleApplication#mixedEvent 主线程：我在这里干等着");
        return "OK";
    }

}
