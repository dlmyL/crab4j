package cn.dlmyl.crab4j.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * AssertUtils
 *
 * @author dlmyL
 */
public class AssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CommonUtils.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection,
                "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CommonUtils.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void notEmpty(String str, String message) {
        if (CommonUtils.isEmpty(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String str) {
        if (CommonUtils.isEmpty(str)) {
            notEmpty(str, "[Assertion failed] - this string must not be empty");
        }
    }

}
