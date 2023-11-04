package cn.dlmyl.crab4j.annotation;

import cn.dlmyl.crab4j.constant.Constants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件订阅注解，用在所订阅的方法上
 *
 * @author dlmyL
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sub {

    String topic() default Constants.DEFAULT_TOPIC;

}
