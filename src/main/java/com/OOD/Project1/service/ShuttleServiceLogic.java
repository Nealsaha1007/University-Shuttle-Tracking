package com.OOD.Project1.service;

import com.OOD.Project1.model.Shuttle;
import com.OOD.Project1.model.Student;
import com.OOD.Project1.repository.ShuttleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShuttleServiceLogic implements ShuttleService {

    private final ShuttleRepo shuttleRepo;
    private final StudentService studentService;

    public ShuttleServiceLogic(ShuttleRepo shuttleRepo, StudentService studentService) {
        this.shuttleRepo = shuttleRepo;
        this.studentService = studentService;
    }

    @Override
    public List<Shuttle> getShuttles() {
        return shuttleRepo.findAll();
    }

    @Override
    public String addPassenger(Long suid, String dropAddress) {

        // Checking if the user exists in the db
        boolean valid = studentService.isValid(suid);
        if (!valid) {
            return " The entered user id is invalid";
        }

        List<Shuttle> allShuttles=  getShuttles();

        // Filter All Shuttles which are assigned to 5 or less students

        List<Shuttle> filteredshuttle = allShuttles.stream()
                .filter(c ->c.getStudentSet().size()<5)
                .collect(Collectors.toList());


        Shuttle shuttle =filteredshuttle.get(0);
        if (filteredshuttle.size() >0 ) {

            // Have a check here if the student has already assigned a shuttle
            // this check is important to avoid duplicacy

            Student student=studentService.getStudent(suid);

            if (student.getShuttle()==null) {

                // such shuttle exists so add passengers to it
                // and also increase the passendgersCount in shuttle



                studentService.updateStudent(suid, shuttle,dropAddress);
                return "The Student Has Been Added to Shuttle -  "+shuttle.getBusId();

            }
            else {
                return "The Student Has Been Already Added to Shuttle - "+student.getShuttle().getBusId();

            }

        }
        else {
            return "Shuttle is not available now";

        }

    }


	@Override
    public String dropPassenger(Long busID) {
		
		Shuttle shuttle=shuttleRepo.findById(busID).get();

		System.out.println(busID);

    	// Checking if the user exists in the db 
        boolean valid = shuttle !=null?true:false;
        if (!valid) {
            return " The entered user id is invalid";
        }

        List<Student> student = studentService.getStudentByShuttle(shuttle);
        // Make the bus available at stop
        
        if (student.size() == 0) {
        	// no student here now bus is parked
        	shuttle.setAtStopConfirmation(true);
        	shuttleRepo.save(shuttle);
        	return "No students to drop";
        
        }else {
        	  if (student.get(0).getShuttle()!=null) {
        	      

                 	// Remove the shuttle from the student too
                  var dropAddress = student.get(0).getDropAddress();
                  studentService.updateStudent(student.get(0).getId(), null,null);
                  return "Student has been dropped from shuttle with busId - "+student.get(0).getId() + " dropped at " + dropAddress;
              	
              }else {
              	return "No shuttle Assigned to the student";
              }
        }

    }
    


    @Override
    public String requestPickup(Long suid) {

        boolean valid = studentService.isValid(suid);
        if (!valid) {
            return "Sorry invalid SUID";
        }

        if (getShuttles() == null || getShuttles().size() == 0) {
            return "Shuttle service unavailable";
        }
        
        List<Shuttle> allShuttles = getShuttles();

        List<Shuttle> filteredShuttle = allShuttles.stream()
        .filter(c ->c.isAtStopConfirmation())
        .collect(Collectors.toList());

        if (filteredShuttle.size() == 0) {
            return  "ETA: 0 mins";

        }else {
            int sizeShuttle1 = allShuttles.get(0).getStudentSet().size();
            int sizeShuttle2 = allShuttles.get(1).getStudentSet().size();
            return "Wait time: "+String.valueOf(Integer.min(sizeShuttle1,sizeShuttle2) * 2) + " mins";
        }

    }
}
