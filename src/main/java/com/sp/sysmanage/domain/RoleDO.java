package com.sp.sysmanage.domain;

public class RoleDO {
	
	private Integer roleID;
	private String roleName;
	private String roleDesc;
	
	public Integer getRoleID() {
		return roleID;
	}
	
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleDesc() {
		return roleDesc;
	}
	
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String toString() {
		return "roleDO {" +
				"roleID='" + roleID + '\'' +
				"roleName=" + roleName +
				"roleDesc=" + roleDesc;
	}
}
