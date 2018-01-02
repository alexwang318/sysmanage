package com.oracle.sp.exception;

public class MachineTypeServiceException extends BusinessException {
    public MachineTypeServiceException(){
        super();
    }

    public MachineTypeServiceException(Exception e){
        super(e);
    }

    public MachineTypeServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public MachineTypeServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
