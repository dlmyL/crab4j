package cn.dlmyl.crab4j.starter.annotation;

import cn.dlmyl.crab4j.starter.config.Crab4JAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启事件驱动
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(Crab4JAutoConfiguration.class)
public @interface EnableCrab4J {

}
