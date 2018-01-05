package com.oracle.sp.exception;

public class ServerTypeServiceException extends BusinessException {
    public ServerTypeServiceException(){
        super();
    }

    public ServerTypeServiceException(Exception e){
        super(e);
    }

    public ServerTypeServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public ServerTypeServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
