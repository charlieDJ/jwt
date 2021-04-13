package com.example.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "`dept`")
@JsonIgnoreProperties(value = {"handler"})
public class Dept {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`tel`")
    private String tel;

    @Column(name = "`address`")
    private String address;

    @Column(name = "`dept_name`")
    private String deptName;

    private Set<User> users;

}