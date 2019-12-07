package com.ruiyun.data.jdbc.bean;

public class Comsumer {
	
	private long id;
	private String  firstName;
	private String lastName;
	
	public Comsumer(long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Comsumer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
