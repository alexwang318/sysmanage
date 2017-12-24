package com.oracle.sp.exception;

public class UserManageServiceException extends BusinessException {
    public UserManageServiceException(){
        super();
    }

    public UserManageServiceException(Exception e, String exception){
        super(e, exception);
    }

    public UserManageServiceException(Exception e){
        super(e);
    }
}
