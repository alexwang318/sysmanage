package com.oracle.sp.exception;

public class LabLocationServiceException extends BusinessException {
    public LabLocationServiceException(){
        super();
    }

    public LabLocationServiceException(Exception e){
        super(e);
    }

    public LabLocationServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public LabLocationServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
