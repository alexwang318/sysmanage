package com.oracle.sp.domain;

public class MachineInfoDO {
	private Integer id;
	private String label;
	private String PN;
	private String SN;
	private String spMac;
	private String spIP;
	private String spTelnetIP;
	private String spTelnetPort;
	private String state;
	private String position;
	private String log;

	public Integer getMachineID() {
		return id;
	}

	public void setMachineID(Integer id) {
		this.id = id;
	}

	public String getMachineLabel() {
		return label;
	}

	public void setMachineLabel(String label) {
		this.label = label;
	}

	public String getMachinePN() {
		return PN;
	}

	public void setMachinePN(String PN) {
		this.PN = PN;
	}

	public String getMachineSN() {
		return SN;
	}

	public void setMachineSN(String SN) {
		this.SN = SN;
	}

	public String getSpMac() {
		return spMac;
	}

	public void setSpMac(String spMac) {
		this.spMac = spMac;
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

	public String getMachineState() {
		return state;
	}

	public void setMachineState(String state) {
		this.state = state;
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
        return "MachineInfoDTO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", PN='" + PN + '\'' +
                ", SN='" + SN + '\'' +
                ", spMac='" + spMac + '\'' +
                ", spIP='" + spIP + '\'' +
                ", spTelnetIP='" + spTelnetIP + '\'' +
                ", spTelnetPort='" + spTelnetPort + '\'' +
                ", state=" + state +
                ", position='" + position + '\'' +
                ", log='" + log + '\'' +
                '}';
	}
	
	

}
