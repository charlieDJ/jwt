package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.model.Customer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("select * from customer where customer_id = #{uid}")
    @Results(id = "userMap", value = {
            @Result(property = "tickets", column = "customer_id", javaType = List.class,
                    many = @Many(select = "com.example.boot.dao.mapper.TicketMapper.findTicketByCid",
                            fetchType = FetchType.LAZY))
    })
    Optional<Customer> findById(@Param("uid") String uid);
}
