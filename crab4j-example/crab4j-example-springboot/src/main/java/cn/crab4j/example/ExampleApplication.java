package cn.crab4j.example;

import cn.crab4j.starter.annotation.EnableCrab4J;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ExampleApplication
 *
 * @author dlmyL
 */
@EnableCrab4J
@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

}
