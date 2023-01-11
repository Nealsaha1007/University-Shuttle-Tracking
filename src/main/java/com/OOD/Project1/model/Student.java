package com.OOD.Project1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Student {


    @Id
  //  @GeneratedValue(strategy=GenerationType.AUTO)  //making the id to be generated automatically
     private Long id;

    private String homeAddress;
    private String dropAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Shuttle shuttle;
    


    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getHomeAddress() {
		return homeAddress;
	}



	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}



	public String getDropAddress() {
		return dropAddress;
	}



	public void setDropAddress(String dropAddress) {
		this.dropAddress = dropAddress;
	}



	public Shuttle getShuttle() {
		return shuttle;
	}



	public void setShuttle(Shuttle shuttle) {
		this.shuttle = shuttle;
	}


	public Student() {
        
    }

	public Student(Long id, String homeAddress) {
        this.id = id;
        this.homeAddress = homeAddress;
    }
}