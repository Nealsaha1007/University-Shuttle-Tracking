package com.OOD.Project1;

import com.OOD.Project1.model.Shuttle;
import com.OOD.Project1.model.Student;
import com.OOD.Project1.repository.ShuttleRepo;
import com.OOD.Project1.repository.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class ShuttleApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShuttleApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDB(ShuttleRepo shuttleRepo, StudentRepo studentRepo) {
        return args -> {

            /*Shuttle shuttle = new Shuttle();

            Student student1 = new Student("address 1", shuttle);
            Student student2 = new Student("address 2", shuttle);
            Student student3 = new Student("address 3", shuttle);

            shuttle.addStudent(student1);
            shuttle.addStudent(student2);
            shuttle.addStudent(student3);

            shuttleRepository.save(shuttle);*/

            Student s0 = new Student(123456786L, "Address 0");
            Student s1 = new Student(123456787L, "Address 1");
            Student s2 = new Student(123456788L, "Address 2");
            Student s3 = new Student(123456789L, "Address 3");
            Student s4 = new Student(123562543L, "Address 4");
            studentRepo.saveAll(List.of(s0, s1, s2, s3, s4));

            Shuttle shuttle1 = new Shuttle(1L);
            Shuttle shuttle2 = new Shuttle(2L);
            shuttleRepo.save(shuttle1);
            shuttleRepo.save(shuttle2);

        };
    }
}
