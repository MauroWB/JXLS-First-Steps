package com.smeup.jxlsexample;

public class Student {
	
	private String name;
	private String surname;
	private String birthdate;
	private int id;
	private int avg;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAvg() {
		return avg;
	}

	public void setAvg(Integer avg) {
		this.avg = avg;
	}

	public Student(String name, String surname, String date, int id, int avg) {
		this.setAvg(avg);
		this.setName(name);
		this.setSurname(surname);
		this.setBirthdate(date);
		this.setId(id);
	}
}
