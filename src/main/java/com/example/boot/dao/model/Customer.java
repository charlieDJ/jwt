package com.example.boot.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Customer {
    private Integer customerId;
    private String customerName;
    private Integer customerTel;
    private List<Ticket> tickets; //使用一个List来表示车票

}