package com.OOD.Project1.dtos;

public class AddStudenttoShuttleRequest {
	
	private String dropAddress;
	private String pickUpAddress;
	private long id;
	public String getDropAddress() {
		return dropAddress;
	}
	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	

}
