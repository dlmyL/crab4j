package cn.crab4j.example.controller;

import cn.crab4j.example.framework.event.SimpleAsyncEvent;
import cn.crab4j.example.framework.event.SimpleEvent;
import cn.dlmyl.crab4j.starter.Pub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExampleController
 *
 * @author dlmyL
 */
@RestController
public class ExampleController {

    @GetMapping("/event")
    public String event() {
        Pub.X.event(new SimpleEvent.Builder().message("Uzi 来全杀了").build(), true);
        System.out.println("ExampleController#event 同步事件：我在这里干等着");
        return "OK";
    }

    @GetMapping("/async-event")
    public String asyncEvent() {
        Pub.X.event(new SimpleAsyncEvent.Builder().message("Shy 哥天神下凡").build());
        System.out.println("ExampleController#asyncEvent 异步事件：我可以干其他事情");
        return "OK";
    }

    @GetMapping("/mixed-event")
    public String mixedEvent() {
        Pub.X.event(new SimpleAsyncEvent.Builder().message("Shy 哥天神下凡").build());
        System.out.println("ExampleController#mixedEvent 异步事件：我可以干其他事情");

        Pub.X.event(new SimpleEvent.Builder().message("Uzi 来全杀了").build(), true);
        System.out.println("ExampleController#event 同步事件：我在这里干等着");
        return "OK";
    }

}
