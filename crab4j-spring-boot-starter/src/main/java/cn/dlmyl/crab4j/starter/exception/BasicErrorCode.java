package cn.dlmyl.crab4j.starter.exception;

/**
 * 基本错误代码
 *
 * @author <a href="https://github.com/dlmyL">dlmyL</a>
 * @version 1.0
 */
public enum BasicErrorCode implements ErrorCode {

    CRAB4J_FRAMEWORK_ERROR("CRAB4J_FRAMEWORK_ERROR" , "CRAB4J框架内部错误"),

    UNKNOWN_ERROR("UNKNOWN_ERROR" , "未知的系统错误");

    private String errCode;
    private String errMsg;

    BasicErrorCode(String errCode, String errDesc){
        this.errCode = errCode;
        this.errMsg = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

}
