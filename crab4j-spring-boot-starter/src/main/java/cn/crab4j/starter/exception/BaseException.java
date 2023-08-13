package cn.crab4j.starter.exception;

/**
 * 基础异常抽象
 *
 * @author dlmyL
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errCode;

    public BaseException(String errMsg){
        super(errMsg);
    }

    public BaseException(String errMsg, Throwable e) {
        super(errMsg, e);
    }

    public ErrorCode getErrCode() {
        return errCode;
    }

    public void setErrCode(ErrorCode errCode) {
        this.errCode = errCode;
    }

}
