package cn.dlmyl.crab4j.core.route;

import cn.dlmyl.crab4j.core.route.impl.PerThreadExecutorRouter;

/**
 * ExecutorRouteEnum
 *
 * @author dlmyL
 */
public enum ExecutorRouteEnum {

    PER_THREAD("per", new PerThreadExecutorRouter());

    private final String name;
    private final ExecutorRouter router;

    public String getName() {
        return name;
    }

    public ExecutorRouter getRouter() {
        return router;
    }

    ExecutorRouteEnum(String name, ExecutorRouter executorRouter) {
        this.name = name;
        this.router = executorRouter;
    }

    public static ExecutorRouteEnum match(String name, ExecutorRouteEnum defaultItem) {
        if (name != null) {
            for (ExecutorRouteEnum item : ExecutorRouteEnum.values()) {
                if (item.getName().equals(name)) {
                    return item;
                }
            }
        }
        return defaultItem;
    }

}
