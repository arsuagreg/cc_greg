package com.jagdon.careconnect.sysarch.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jagdon.careconnect.sysarch.api.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    User findByUsername(String username);
}
