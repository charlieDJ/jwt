package com.example.boot.dao.mapper;

import com.example.boot.dao.BaseMapper;
import com.example.boot.dao.model.Ticket;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMapper extends BaseMapper<Ticket> {

    @Select("select * from ticket where ticket_cid =#{cid}")
    List<Ticket> findTicketByCid(@Param("cid") Integer cid);

}
