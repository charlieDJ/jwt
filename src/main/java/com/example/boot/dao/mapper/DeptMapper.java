package com.example.boot.dao.mapper;

import com.example.boot.entity.Dept;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptMapper {

    Optional<Dept> getById(@Param("id") Integer id);
}
