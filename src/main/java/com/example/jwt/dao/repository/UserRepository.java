package com.example.jwt.dao.repository;

import com.example.jwt.dao.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
