package cn.crab4j.starter.exception;

import cn.crab4j.starter.core.listener.EventListener;
import cn.crab4j.starter.logger.Logger;
import cn.crab4j.starter.logger.LoggerFactory;

/**
 * 默认异常处理器
 *
 * @author dlmyL
 */
@SuppressWarnings("rawtypes")
public class DefaultExceptionHandler implements ExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    public static DefaultExceptionHandler singleton = new DefaultExceptionHandler();

    @Override
    public void handler(EventListener listener, Exception exception) {
        printLog(listener, exception);
    }

    private void printLog(EventListener listener, Exception exception) {
        String className = listener.getClass().getSimpleName();
        if (exception instanceof BaseException) {
            ErrorCode errorCode = ((BaseException) exception).getErrCode();
            logger.warn("BaseException: className: %s, errorCode: %s, errorMsg: %s",
                    className, errorCode.getErrCode(), errorCode.getErrMsg());
        } else {
            logger.error("DefaultException: className: %s, errorCode: %s, errorMsg: %s",
                    className, BasicErrorCode.CRAB4J_FRAMEWORK_ERROR.getErrCode(),
                    BasicErrorCode.CRAB4J_FRAMEWORK_ERROR.getErrMsg());
        }
    }

}
