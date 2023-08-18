package cn.dlmyl.crab4j.starter.exception;

/**
 * 基本错误代码
 *
 * @author dlmyL
 */
public enum BasicErrorCode implements ErrorCode {

    CRAB4J_FRAMEWORK_ERROR("CRAB4J_FRAMEWORK_ERROR" , "CRAB4J框架内部错误"),

    CRAB4J_UNKNOWN_ERROR("CRAB4J_UNKNOWN_ERROR" , "CRAB4J框架未知错误");

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
