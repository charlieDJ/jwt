package com.example.boot.util;

import com.example.boot.common.util.BeanUtilCopy;
import com.example.boot.entity.Dept;
import com.example.boot.model.response.DeptData;
import org.junit.Test;

/**
 * @author dj
 * @date 2021/7/21
 */
public class BeanCopyTest {

    @Test
    public void copy(){
        Dept dept = new Dept();
        dept.setId(1)
                .setAddress("杜曼可")
                .setDeptName("x信维")
                .setTel("3334968");
        DeptData data = BeanUtilCopy.copyProps(dept, DeptData::new, (s, t) -> {
            t.setDname(s.getDeptName());
            t.setLoc(s.getAddress());
        });
        System.out.println(data);
    }

}
