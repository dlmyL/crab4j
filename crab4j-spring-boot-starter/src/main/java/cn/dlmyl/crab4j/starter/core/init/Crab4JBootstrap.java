package cn.dlmyl.crab4j.starter.core.init;

import cn.dlmyl.crab4j.starter.core.event.EventRegister;
import cn.dlmyl.crab4j.starter.core.event.Sub;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Crab4J 程序引导
 *
 * @author <a href="https://dlmyl.github.io">dlmyL</a>
 */
public class Crab4JBootstrap implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Crab4JBootstrap.class);

    @Autowired
    private EventRegister eventRegister;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 注册入口
     */
    @SuppressWarnings("rawtypes")
    public void init() {
        logger.info("开始注册EventListener...");
        Map<String, Object> eventListenerBeans = applicationContext.getBeansWithAnnotation(Sub.class);
        eventListenerBeans.values().forEach(
                listener -> eventRegister.doRegistration((EventListener) listener)
        );
        logger.info("EventListener注册完成...");
    }

}
