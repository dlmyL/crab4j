package cn.crab4j.core.executor;

import cn.crab4j.core.context.EventContext;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 抽像执行器
 *
 * @author dlmyL
 */
public abstract class AbstractExecutor {

    // fixme 后续进行扩展

    public <R, T> R execute(Class<T> targetClz, EventContext context, Function<T, R> exeFunction) {
        T component = locateComponent(targetClz, context);
        return exeFunction.apply(component);
    }

    public <T> void executeVoid(Class<T> targetClz, EventContext context, Consumer<T> exeFunction) {
        T component = locateComponent(targetClz, context);
        exeFunction.accept(component);
    }

    protected abstract <C> C locateComponent(Class<C> targetClz, EventContext context);

}
