package com.jagdon.careconnect.sysarch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagdon.careconnect.sysarch.api.model.Doctor;
import com.jagdon.careconnect.sysarch.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public List<Doctor> getDoctorsByName(String name) {
        return doctorRepository.findByName(name);
    }

    public List<Doctor> getDoctorsBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    public List<Doctor> getDoctorsByLocation(String location) {
        return doctorRepository.findByLocation(location);
    }

    public void addDoctor(Doctor doctor) {
        // Find the current maximum doctorId
        List<Doctor> doctors = doctorRepository.findAll();
        int maxDoctorId = doctors.stream()
                                 .mapToInt(Doctor::getDoctorId)
                                 .max()
                                 .orElse(0);
        
        // Set the new doctor's doctorId to maxDoctorId + 1
        doctor.setDoctorId(maxDoctorId + 1);
        doctorRepository.save(doctor);
    }
}
