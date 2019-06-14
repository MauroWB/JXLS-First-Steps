package com.smeup.jxlsxml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PenX {
	private String name;
	private int srNo;
	
	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	public int getSrNo() {
		return srNo;
	}
	
	@XmlAttribute
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	
	public String TestMessage() {
		return "Test";
	}
	
	@Override
	public String toString() {
		return getName() + ", " + getSrNo();
	}
}
