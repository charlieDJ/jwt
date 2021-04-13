package com.example.boot.dao.mapper;

import com.example.boot.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author dengjia
 * @date 2019/8/15 15:57
 */
@Repository
public interface UserMapper extends BaseMapper<User, Integer> {

    Optional<User> getByName(@Param("username") String username);

    List<User> getAll();

    Set<User> findAllByDeptId(@Param("deptId") String deptId);
}
