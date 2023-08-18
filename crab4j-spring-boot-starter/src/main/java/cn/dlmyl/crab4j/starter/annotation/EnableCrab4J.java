package cn.dlmyl.crab4j.starter.annotation;

import cn.dlmyl.crab4j.starter.config.Crab4JAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在SpringBoot启动类上添加该注解开启 Crab4J 功能
 *
 * @author dlmyL
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(Crab4JAutoConfiguration.class)
public @interface EnableCrab4J {

}
