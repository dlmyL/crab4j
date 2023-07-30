package com.crab4j.core.common.builder;

import java.io.Serializable;

/**
 * Builder
 *
 * @author dlmyL
 * @date 2023-07-30
 */
public interface Builder<T> extends Serializable {

    T build();

}
