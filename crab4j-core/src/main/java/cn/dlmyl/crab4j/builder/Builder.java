package cn.dlmyl.crab4j.builder;

import java.io.Serializable;

/**
 * Builder.
 *
 * @author dlmyL
 */
public interface Builder<T> extends Serializable {

    T build();

}
