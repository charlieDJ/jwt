package com.example.boot.repository;

import com.example.boot.dao.model.Dept;

import java.util.Optional;

public interface DeptRepository {
    Optional<Dept> getById(Integer id);
}
