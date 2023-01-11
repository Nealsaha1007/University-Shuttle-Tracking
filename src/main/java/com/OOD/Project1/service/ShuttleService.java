package com.OOD.Project1.service;

import com.OOD.Project1.model.Shuttle;

import java.util.List;

public interface ShuttleService {
    List<Shuttle> getShuttles();

    String addPassenger(Long suid, String dropAddress);//,String pickup);


    String requestPickup(Long suid);



	String dropPassenger(Long busID);
}
