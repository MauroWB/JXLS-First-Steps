package com.smeup.jxlsexample;

public class Pen {
	private String name;
	private int srNo;
	private String desc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	
	public Pen(String n, int sn) {
		setName(n);
		setSrNo(sn);
	}
	
	public Pen(int srN, String name2, String desc) {
		setSrNo(srN);
		setName(name2);
		setDesc(desc);
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String TestMessage() {
		return "Test";
	}
	
	@Override
	public String toString() {
		return getName() + ", " + getSrNo();
	}
}
