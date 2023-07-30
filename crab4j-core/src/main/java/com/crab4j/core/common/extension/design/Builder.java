package com.crab4j.core.common.extension.design;

import java.io.Serializable;

/**
 * Builder.
 *
 * @author dlmyL
 * @date 2023-07-29
 */
public interface Builder<T> extends Serializable {

    T build();

}
