package com.example.boot.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@Entity
@Table(name = "`jd_user`")
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

}
