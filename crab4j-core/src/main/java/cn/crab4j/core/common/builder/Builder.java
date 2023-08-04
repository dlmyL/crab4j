package cn.crab4j.core.common.builder;

import java.io.Serializable;

/**
 * Builder.
 *
 * @author dlmyL
 */
public interface Builder<T> extends Serializable {

    T build();

}
