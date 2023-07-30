package com.crab4j.core.toolkit;

import java.util.Collection;
import java.util.Map;

/**
 * 公共常用相关的工具类
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public class CommonUtils {

    /**
     * 判断是否为空（字符串、Collection、map） <br>
     * null,"", "null" 返回 false <br>
     * Collection,map 为空或者 size == 0 返回 false
     *
     * @return true 为空 false 不为空
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).length() == 0 || "null".equals(obj);
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size() == 0;
        }
        return false;
    }

    /**
     * 判断是否为空（字符串、Collection、map） <br>
     * null,"", "null" 返回 false <br>
     * Collection,map 为空或者 size == 0 返回 false
     *
     * @return true 不为空 false 为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

}
