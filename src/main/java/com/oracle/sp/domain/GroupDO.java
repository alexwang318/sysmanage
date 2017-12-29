package com.oracle.sp.domain;

public class GroupDO {
	private Integer groupID;
	private String groupName;
	private String groupDesc;
	
	public Integer getGroupID() {
		return groupID;
	}
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	public String toString() {
		return "GroupDO {" +
				"groupID='" + groupID + '\'' +
				"groupName=" + groupName +
				"groupDesc=" + groupDesc;
	}
}
