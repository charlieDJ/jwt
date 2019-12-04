package com.example.boot.repository.impl;

import com.example.boot.dao.mapper.DeptMapper;
import com.example.boot.dao.model.Dept;
import com.example.boot.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DeptRepositoryImpl implements DeptRepository {

    @Autowired
    private DeptMapper deptMapper;

    @Cacheable(value = "dept", sync = true, key = "#id")
    @Override
    public Optional<Dept> getById(Integer id) {
        return Optional.ofNullable(deptMapper.selectByPrimaryKey(id));
    }
}
