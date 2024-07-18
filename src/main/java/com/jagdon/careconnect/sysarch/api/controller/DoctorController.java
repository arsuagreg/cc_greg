package com.jagdon.careconnect.sysarch.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jagdon.careconnect.sysarch.api.model.Doctor;
import com.jagdon.careconnect.sysarch.service.DoctorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> getDoctors(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String specialty,
        @RequestParam(required = false) String location) {
        
        if (name != null) {
            return ResponseEntity.ok(doctorService.getDoctorsByName(name));
        } else if (specialty != null) {
            return ResponseEntity.ok(doctorService.getDoctorsBySpecialty(specialty));
        } else if (location != null) {
            return ResponseEntity.ok(doctorService.getDoctorsByLocation(location));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Doctor added successfully");
    }
}
