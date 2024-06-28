package com.jagdon.careconnect.sysarch.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jagdon.careconnect.sysarch.api.model.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, Integer> {
    List<Doctor> findByName(String name);
    List<Doctor> findBySpecialty(String specialty);
    List<Doctor> findByLocation(String location);
}
