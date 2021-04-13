package com.example.boot.mapper;

import com.example.boot.JwtApplicationTests;
import com.example.boot.dao.mapper.DeptMapper;
import com.example.boot.entity.Dept;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dengjia on 2020/1/9
 */
public class DeptMapperTest extends JwtApplicationTests {


    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void getTest() {
        final Dept deptOpt = deptMapper.selectByPrimaryKey(1);
        System.err.println(deptOpt.getTel());
    }

}
