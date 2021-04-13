package com.example.boot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Entity
@Table(name = "`jd_user`")
@JsonIgnoreProperties(value = {"handler"})
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Transient
    private Dept dept;

    private String deptId;

}
