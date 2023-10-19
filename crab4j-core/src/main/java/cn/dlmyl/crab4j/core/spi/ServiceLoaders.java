package cn.dlmyl.crab4j.core.spi;

import java.util.ServiceLoader;

/**
 * ServiceLoaders
 *
 * @author dlmyL
 */
public class ServiceLoaders {

    public static <T> T loadDefault(Class<T> serviceClass) {
        return ServiceLoader.load(serviceClass).iterator().next();
    }

}
