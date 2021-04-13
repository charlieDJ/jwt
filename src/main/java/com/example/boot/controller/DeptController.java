package com.example.boot.controller;

import com.example.boot.dao.mapper.DeptMapper;
import com.example.boot.entity.Dept;
import com.example.boot.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dj
 * @date 2021/4/12
 */
@Api(tags = {"用户接口"})
@RestController
@RequestMapping("/dept")
@Validated
public class DeptController {

    @Autowired
    private DeptMapper deptMapper;

    @ApiOperation(value = "获取部门", notes = "获取部门")
    @GetMapping("/getDeptById")
    public Response<Object> getByName(@RequestParam("deptId") Integer deptId) {
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        return Response.success(dept);
    }
}
