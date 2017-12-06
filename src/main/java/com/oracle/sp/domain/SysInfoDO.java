package com.oracle.sp.domain;

public class SysInfoDO {
	
	private Integer sysID;
	private String sysType;
	private String sysPN;
	private String sysSN;
	private String spMAC;
	private String spIP;
	private String spTelnetIP;
	private String spTelnetPort;
	private String sysLabel;
	private Integer sysState;
	private String position;
	private String log;
	
	public Integer getSysID() {
		return sysID;
	}
	
	public void setSysID(Integer sysID) {
		this.sysID = sysID;
	}
	
	public String getSysType() {
		return sysType;
	}
	
	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	
	public String getSysPN() {
		return sysPN;
	}
	
	public void setSysPN(String sysPN) {
		this.sysPN = sysPN;
	}
	
	public String getSysSN() {
		return sysSN;
	}
	
	public void setSysSN(String sysSN) {
		this.sysSN = sysSN;
	}
	
	public String getSpMAC() {
		return spMAC;
	}
	
	public void setSpMAC(String spMAC) {
		this.spMAC = spMAC;
	}
	
	public String getSpIP() {
		return spIP;
	}
	
	public void setSpIP(String spIP) {
		this.spIP = spIP;
	}
	
	public String getSpTelnetIP() {
		return spTelnetIP;
	}
	
	public void setSpTelnetIP(String spTelnetIP) {
		this.spTelnetIP = spTelnetIP;
	}
	
	public String getSpTelnetPort() {
		return spTelnetPort;
	}
	
	public void setSpTelnetPort(String spTelnetPort) {
		this.spTelnetPort = spTelnetPort;
	}	
	
	public String getSysLabel() {
		return sysLabel;
	}
	
	public void setSysLabel(String sysLabel) {
		this.sysLabel = sysLabel;
	}	
	
	public Integer getSysState() {
		return sysState;
	}
	
	public void setSysState(Integer sysState) {
		this.sysState = sysState;
	}	
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}	
	
	public String getLog() {
		return log;
	}
	
	public void setLog(String log) {
		this.log = log;
	}
	
	public String toString() {
		return "SysInfoDO {" +
				"sysID=" + sysID +
				"sysType=" + sysType +
				"sysPN=" + sysPN +
				"sysSN=" + sysSN +
				"spMAC=" + spMAC +
				"spIP=" + spIP +
				"spTelnetIP=" + spTelnetIP +
				"spTelnetPort=" + spTelnetPort +
				"sysLabel=" + sysLabel +
				"sysState='" + sysState + '\'' +
				"position=" + position +
				"log=" + log;
	}

}
