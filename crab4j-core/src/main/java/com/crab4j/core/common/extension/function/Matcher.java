package com.crab4j.core.common.extension.function;

/**
 * Matcher.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
@FunctionalInterface
public interface Matcher<T> {

    boolean match(T t);

}
