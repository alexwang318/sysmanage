package com.oracle.sp.domain;

public class SysTypeInfoDO {
	
	private Integer typeID;
	private String sysTypeName;
	private String sysTypeDesc;
	
	public Integer getTypeID() {
		return typeID;
	}
	public void setTypeID(Integer typeID) {
		this.typeID = typeID;
	}
	public String getSysTypeName() {
		return sysTypeName;
	}
	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}
	public String getSysTypeDesc() {
		return sysTypeDesc;
	}
	public void setSysTypeDesc(String sysTypeDesc) {
		this.sysTypeDesc = sysTypeDesc;
	}
	
	@Override
	public String toString() {
		return "SysTypeInfoDO {" +
				"typeID=" + typeID +
				",sysTypeName='" + sysTypeName + "\'" +
				",sysTypeDesc='" + sysTypeDesc + "\'" +
				'}';
	}
	
}
