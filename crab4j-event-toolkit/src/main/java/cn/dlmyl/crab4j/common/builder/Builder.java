package cn.dlmyl.crab4j.common.builder;

import java.io.Serializable;

/**
 * Builder.
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public interface Builder<T> extends Serializable {

    T build();

}
