package com.smeup.jxlsexample;

public class PenAdv {
	
	public String name;
	public float cost;
	public String desc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public PenAdv(String n, float c, String d) {
		setName(n);
		setCost(c);
		setDesc(d);
	}
}
