package com.oracle.sp.domain;

public class GroupDO {
	private Integer id;
	private String name;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return "GroupDO {" +
				"id=" + id + '\'' +
				"name='" + name + '\'' +
				"description='" + description + '\'' +
				"}";
	}
}
