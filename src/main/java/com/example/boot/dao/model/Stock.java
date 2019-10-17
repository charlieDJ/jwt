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
@Table(name = "`stock`")
public class Stock {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 库存
     */
    @Column(name = "`count`")
    private Integer count;

    /**
     * 已售
     */
    @Column(name = "`sale`")
    private Integer sale;

    /**
     * 乐观锁，版本号
     */
    @Column(name = "`version`")
    private Integer version;
}