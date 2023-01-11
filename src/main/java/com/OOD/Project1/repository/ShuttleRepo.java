package com.OOD.Project1.repository;

import com.OOD.Project1.model.Shuttle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShuttleRepo extends JpaRepository<Shuttle, Long> {

}
