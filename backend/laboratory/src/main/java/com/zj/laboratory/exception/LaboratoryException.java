package com.zj.laboratory.exception;


import com.zj.laboratory.enums.ResultEnum;

/**
 * 自定义异常
 *
 * @date
 */
public class LaboratoryException extends RuntimeException {

    private static final long serialVersionUID = 2450214686001409867L;

    private Integer errorCode = ResultEnum.ERROR.getCode();

    public LaboratoryException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.errorCode = resultEnum.getCode();
    }

    public LaboratoryException(ResultEnum resultEnum, Throwable throwable) {
        super(resultEnum.getMsg(), throwable);
        this.errorCode = resultEnum.getCode();
    }

    public LaboratoryException(Integer errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public LaboratoryException(String msg) {
        super(msg);
    }

    public LaboratoryException(Throwable throwable) {
        super(throwable);
    }

    public LaboratoryException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
