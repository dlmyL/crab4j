package com.crab4j.core.annotation;

import com.crab4j.core.common.constant.Constants;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Subscribe.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    String topic() default Constants.DEFAULT_TOPIC;

}
