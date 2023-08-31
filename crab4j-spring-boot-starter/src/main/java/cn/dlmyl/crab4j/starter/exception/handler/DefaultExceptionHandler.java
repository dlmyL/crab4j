package cn.dlmyl.crab4j.starter.exception.handler;

import cn.dlmyl.crab4j.starter.core.listener.EventListener;
import cn.dlmyl.crab4j.starter.exception.BaseException;
import cn.dlmyl.crab4j.starter.exception.BasicErrorCode;
import cn.dlmyl.crab4j.starter.exception.ErrorCode;
import cn.dlmyl.crab4j.starter.logger.Logger;
import cn.dlmyl.crab4j.starter.logger.LoggerFactory;

/**
 * 默认异常处理器
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public enum DefaultExceptionHandler implements ExceptionHandler {

    X;

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

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
