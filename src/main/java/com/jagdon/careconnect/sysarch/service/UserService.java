package com.jagdon.careconnect.sysarch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jagdon.careconnect.sysarch.api.model.User;
import com.jagdon.careconnect.sysarch.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public boolean registerUser(String email, String username, String password) {
        User existingUser = getUserByUsername(username);
        if (existingUser != null) {
            return false;
        }

        int newId = (int) userRepository.count() + 1;
        User newUser = new User(newId, "", 0, email, username, password);
        userRepository.save(newUser);
        return true;
    }

    public List<User> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
