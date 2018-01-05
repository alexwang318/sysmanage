package com.oracle.sp.domain;

public class ServerTypeDO {
	
	private Integer id;
	private String name;
	private String url;
	private String description;
	private byte[] picture;
	
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
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "ServerTypeDO {" +
				"id=" + id +
				",name='" + name + "\'" +
				",url='" + url + "\'" +
				",description='" + description + "\'" +
				'}';
	}
}
