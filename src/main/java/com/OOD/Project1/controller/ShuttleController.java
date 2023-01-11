package com.OOD.Project1.controller;

import com.OOD.Project1.dtos.AddStudenttoShuttleRequest;
import com.OOD.Project1.dtos.Response;
import com.OOD.Project1.service.ShuttleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/shuttle")
public class ShuttleController {

    private final ShuttleService shuttleServiceInstance;
    public ShuttleController(ShuttleService shuttleServiceInstance) {
        this.shuttleServiceInstance = shuttleServiceInstance;
    }

    //requestPickup?suid=123456789    (http://localhost:8080/api/v1/shuttle/requestPickup?suid=123456786)
    @GetMapping("/requestPickup")
    public ResponseEntity<Response> requestPickup(@RequestParam Long suid) {
    	var response=new Response();
    	response.setMessage(shuttleServiceInstance.requestPickup(suid));
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    

    //http://ip/addPassenger?suid=123456789&address="123 elm st, city, ST zip"
    @PostMapping("/addPassenger")
    public ResponseEntity<Response> addPassenger(@RequestBody AddStudenttoShuttleRequest ads) {
    	var response=new Response();
    	response.setMessage(shuttleServiceInstance.addPassenger(ads.getId(), ads.getDropAddress()));//,ads.getPickUpAddress()));
    	return new ResponseEntity<>(response, HttpStatus.OK);
        
    }

    //http://ip/dropPassenger?shuttleId=123456789"
    @GetMapping("/dropPassenger")
    public ResponseEntity<Response> dropPassenger(@RequestParam Long shuttleId) {
    	var response=new Response();
    	response.setMessage(shuttleServiceInstance.dropPassenger(shuttleId));
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/shuttle1/status")
    public ResponseEntity<Response> shuttle1Status() {
        int size = shuttleServiceInstance.getShuttles().get(0).getStudentSet().size();
    	var response=new Response();
    	response.setMessage(String.format("Total passenger on board: %d.", size));
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/shuttle2/status")
    public ResponseEntity<Response> shuttle2Status() {
        int size = shuttleServiceInstance.getShuttles().get(1).getStudentSet().size();
        var response=new Response();
        response.setMessage(String.format("Total passenger on board: %d.", size));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
