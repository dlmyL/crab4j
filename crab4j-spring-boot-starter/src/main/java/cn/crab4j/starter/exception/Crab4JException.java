package cn.crab4j.starter.exception;

/**
 * 框架异常
 *
 * @author dlmyL
 */
public class Crab4JException extends BaseException {

    private static final long serialVersionUID = 1L;

    public Crab4JException(String errMessage) {
        super(errMessage);
        this.setErrCode(BasicErrorCode.CRAB4J_FRAMEWORK_ERROR);
    }

    public Crab4JException(String errMessage, Throwable e) {
        super(errMessage, e);
        this.setErrCode(BasicErrorCode.CRAB4J_FRAMEWORK_ERROR);
    }

}
