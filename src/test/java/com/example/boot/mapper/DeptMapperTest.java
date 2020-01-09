package com.example.boot.mapper;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.DeptMapper;
import com.example.boot.model.response.DeptData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author dengjia on 2020/1/9
 */
public class DeptMapperTest extends JwtApplicationTests {


    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void getTest(){
        final Optional<DeptData> deptOpt = deptMapper.getById(8);
        final DeptData deptData = deptOpt.get();
        System.err.println(deptData.toString());
    }

}
