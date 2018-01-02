package com.oracle.sp.domain;

public class MachineTypeDO {
	
	private Integer id;
	private String name;
	private String desc;
	private byte[] picture;
	
	public Integer getTypeID() {
		return id;
	}
	public void setTypeID(Integer id) {
		this.id = id;
	}
	public String getmachineTypeName() {
		return name;
	}
	public void setmachineTypeName(String name) {
		this.name = name;
	}
	public String getmachineTypeDesc() {
		return desc;
	}
	public void setmachineTypeDesc(String desc) {
		this.desc = desc;
	}
	public byte[] getTypePic() {
		return picture;
	}
	public void setTypePic(byte[] picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "machineTypeInfoDO {" +
				"typeID=" + id +
				",machineTypeName='" + name + "\'" +
				",machineTypeDesc='" + desc + "\'" +
				'}';
	}

	
}
