package com.OOD.Project1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Getter
@Setter
public class Shuttle {

    @Id
    private Long busId;

    private boolean atStopConfirmation;
    
    

    @OneToMany(mappedBy = "shuttle", cascade = CascadeType.ALL)
    private Set<Student> studentSet;

	public Shuttle() {
        studentSet = new HashSet<>();
        atStopConfirmation = true;
    }

    public Shuttle(long busId) {
        this();
        this.busId = busId;
    }


	public boolean passengerOnBoard(Student student) {
		return studentSet.contains(student);
	}

}
