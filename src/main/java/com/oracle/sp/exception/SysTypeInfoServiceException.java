package com.oracle.sp.exception;

public class SysTypeInfoServiceException extends BusinessException {
    public SysTypeInfoServiceException(){
        super();
    }

    public SysTypeInfoServiceException(Exception e){
        super(e);
    }

    public SysTypeInfoServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public SysTypeInfoServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
