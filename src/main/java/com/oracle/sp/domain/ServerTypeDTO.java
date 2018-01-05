package com.oracle.sp.domain;

public class ServerTypeDTO {
	
	private String name;
	private String url;
	private String description;
	private String picturePath;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	@Override
	public String toString() {
		return "ServerTypeDTO {" +
				"name='" + name + "\'" +
				",url='" + url + "\'" +
				",description='" + description + "\'" +
				",picturePath='" + picturePath + "\'" +
				'}';
	}

}
