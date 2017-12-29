package com.oracle.sp.domain;

public class SysInfoDO {
	
	private Integer machineID;
	private String machineType;
	private String machinePN;
	private String machineSN;
	private String spMAC;
	private String spIP;
	private String spTelnetIP;
	private String spTelnetPort;
	private String machineLabel;
	private Integer machineState;
	private String position;
	private String log;
	
	public Integer getSysID() {
		return machineID;
	}
	
	public void setSysID(Integer sysID) {
		this.machineID = sysID;
	}
	
	public String getSysType() {
		return machineType;
	}
	
	public void setSysType(String sysType) {
		this.machineType = sysType;
	}
	
	public String getSysPN() {
		return machinePN;
	}
	
	public void setSysPN(String sysPN) {
		this.machinePN = sysPN;
	}
	
	public String getSysSN() {
		return machineSN;
	}
	
	public void setSysSN(String sysSN) {
		this.machineSN = sysSN;
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
		return machineLabel;
	}
	
	public void setSysLabel(String sysLabel) {
		this.machineLabel = sysLabel;
	}	
	
	public Integer getSysState() {
		return machineState;
	}
	
	public void setSysState(Integer sysState) {
		this.machineState = sysState;
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
				"sysID=" + machineID +
				"sysType=" + machineType +
				"sysPN=" + machinePN +
				"sysSN=" + machineSN +
				"spMAC=" + spMAC +
				"spIP=" + spIP +
				"spTelnetIP=" + spTelnetIP +
				"spTelnetPort=" + spTelnetPort +
				"sysLabel=" + machineLabel +
				"sysState='" + machineState + '\'' +
				"position=" + position +
				"log=" + log;
	}


}
