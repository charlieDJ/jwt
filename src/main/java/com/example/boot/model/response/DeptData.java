package com.example.boot.model.response;

import com.example.boot.common.enumeration.Level;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dengjia on 2020/1/9
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeptData {
    private Integer deptno;

    private String dname;

    private String loc;

    private Level level;
}
