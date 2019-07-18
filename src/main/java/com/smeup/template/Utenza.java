package com.smeup.template;

public class Utenza {
	private String name;
	private int id;
	
	public Utenza(String name, int id) {
		setName(name);
		setId(id);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}