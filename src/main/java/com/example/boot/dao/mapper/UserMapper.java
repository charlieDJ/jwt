package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author dengjia
 * @date 2019/8/15 15:57
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from  jd_user where username = #{username}")
    Optional<User> getByName(@Param("username") String username);
}
