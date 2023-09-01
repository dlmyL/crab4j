package cn.dlmyl.crab4j.starter.core.event;

import cn.dlmyl.crab4j.starter.core.executor.DefaultExecutor;
import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.BaseException;
import cn.dlmyl.crab4j.starter.exception.BasicErrorCode;
import cn.dlmyl.crab4j.starter.exception.Crab4JException;
import cn.dlmyl.crab4j.starter.exception.ErrorCode;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

/**
 * 事件总线
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 2.0
 */
@SuppressWarnings("all")
public class EventBus implements Bus {

    private static final Logger logger = LoggerFactory.getLogger(EventBus.class);

    @Autowired
    private EventManager eventManager;

    @SuppressWarnings("unchecked")
    @Override
    public Response post(Event event) {
        Response response = null;
        EventListener eventListener = null;
        try {
            eventListener = eventManager.getEventListener(event.getClass()).get(0);
            response = eventListener.onMessage(event);
        } catch (Exception exception) {
            response = handleException(eventListener, response, exception);
        }
        return response;
    }

    @Override
    public void asyncPost(Event event) {
        eventManager.getEventListener(event.getClass()).parallelStream().map(listener -> {
            Response response = null;
            try {
                if (null != listener.getExecutor()) {
                    listener.getExecutor().submit(() -> listener.onMessage(event));
                } else {
                    DefaultExecutor.X.submit(() -> listener.onMessage(event));
                }
            } catch (Exception exception) {
                response = handleException(listener, response, exception);
            }
            return response;
        }).collect(Collectors.toList());
    }

    private Response handleException(EventListener handler, Response response, Exception exception) {
        logger.error(exception.getMessage(), exception);
        Class responseClz = eventManager.getResponseRepository(handler);
        try {
            response = (Response) responseClz.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new Crab4JException(e.getMessage());
        }
        if (exception instanceof BaseException) {
            ErrorCode errCode = ((BaseException) exception).getErrCode();
            response.setErrCode(errCode.getErrCode());
        } else {
            response.setErrCode(BasicErrorCode.UNKNOWN_ERROR.getErrCode());
        }
        response.setErrMessage(exception.getMessage());
        return response;
    }

}
