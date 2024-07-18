package com.jagdon.careconnect.sysarch.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jagdon.careconnect.sysarch.api.model.Doctor;
import com.jagdon.careconnect.sysarch.api.model.User;
import com.jagdon.careconnect.sysarch.service.DoctorService;
import com.jagdon.careconnect.sysarch.service.UserService;

import springfox.documentation.annotations.ApiIgnore;


@CrossOrigin(origins = "*")
@RestController
public class UserController {

    private UserService userService;
    private DoctorService doctorService;

    public UserController(UserService userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @ApiIgnore
    @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/user")
    public User getUser(@RequestParam Integer id) {
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("Message: Either of the parameter is null");
        }

        User user = userService.getUserByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Message: Invalid Credentials");
        }

        return ResponseEntity.ok("Message: Log in successfully");
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signUpUser(@RequestBody Map<String, String> signUpData) {
        String email = signUpData.get("email");
        String username = signUpData.get("username");
        String password = signUpData.get("password");
        String confirmPassword = signUpData.get("confirmPassword");

        if (email == null || username == null || password == null || confirmPassword == null) {
            return ResponseEntity.badRequest().body("Message: Missing parameters");
        }

        if (!password.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Message: Passwords do not match");
        }

        boolean isRegistered = userService.registerUser(email, username, password);

        if (isRegistered) {
            return ResponseEntity.ok("Message: Sign up successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Message: Username already exists");
        }
    }

    @GetMapping("/user/doctors/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int doctorId) {
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        return doctor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/user/doctors/search")
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }
}
