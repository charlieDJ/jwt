package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.handler.TimeStringTypeHandler;
import com.example.boot.dao.model.Order;
import com.example.boot.model.response.StockOrderData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author dengjia
 * @date 2019/10/17 14:32
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from stock_order where id = #{id}")
    @Results(value = {
            @Result(column = "create_time", property = "createTime", typeHandler = TimeStringTypeHandler.class),
    })
    Optional<StockOrderData> getById(@Param("id") long id);

}
