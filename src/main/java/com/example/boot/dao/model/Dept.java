package com.example.boot.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`dept`")
public class Dept {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`deptno`")
    private Integer deptno;

    @Column(name = "`dname`")
    private String dname;

    @Column(name = "`loc`")
    private String loc;

    @Column(name = "`level`")
    private String level;
}