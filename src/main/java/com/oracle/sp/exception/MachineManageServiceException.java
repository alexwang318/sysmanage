package com.oracle.sp.exception;

public class MachineManageServiceException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public MachineManageServiceException(){
        super();
    }

    public MachineManageServiceException(Exception e, String exception){
        super(e, exception);
    }

    public MachineManageServiceException(Exception e){
        super(e);
    }
}
