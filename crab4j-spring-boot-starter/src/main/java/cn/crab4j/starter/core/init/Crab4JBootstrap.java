package cn.crab4j.starter.core.init;

import cn.crab4j.starter.core.event.EventRegister;
import cn.crab4j.starter.core.event.Subscribe;
import cn.crab4j.starter.core.listener.EventListener;
import cn.crab4j.starter.logger.Logger;
import cn.crab4j.starter.logger.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Crab4J 引导类
 *
 * @author dlmyL
 */
public class Crab4JBootstrap implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(Crab4JBootstrap.class);

    private final EventRegister eventRegister;

    public Crab4JBootstrap(EventRegister eventRegister) {
        this.eventRegister = eventRegister;
    }

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
        Map<String, Object> eventListenerBeans = applicationContext.getBeansWithAnnotation(Subscribe.class);
        eventListenerBeans.values().forEach(
                listener -> eventRegister.doRegistration((EventListener) listener)
        );
        logger.info("EventListener注册完成...");
    }

}
