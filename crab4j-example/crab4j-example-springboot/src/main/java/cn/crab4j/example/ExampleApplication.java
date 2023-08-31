package cn.crab4j.example;

import cn.crab4j.example.framework.event.SimpleAsyncEvent;
import cn.crab4j.example.framework.event.SimpleEvent;
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

    /*
      使用方式：
        1、pom 中引入依赖
        2、启动类上加上 @EnableCrab4J 注解
        3、执行 Pub.X.event(event);
     */

    @GetMapping("/test")
    public String test() {
        // 发布异步事件（推荐使用）
        Pub.X.event(new SimpleAsyncEvent.Builder().message("async message producer").build());
        System.out.println("异步事件不会阻塞主线程，建议使用");

        // 发布同步事件（会阻塞主线程，一般不建议使用）
        Pub.X.event(new SimpleEvent.Builder().message("sync message producer").build(), true);
        System.out.println("同步事件会阻塞主线程，不建议使用");

        return "OK";
    }

}
