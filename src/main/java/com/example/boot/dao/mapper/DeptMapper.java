package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.model.Dept;
import com.example.boot.model.response.DeptData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptMapper extends BaseMapper<Dept> {

    @Select("select * from `dept` where id = #{id}")
    Optional<DeptData> getById(@Param("id") Integer id);
}
