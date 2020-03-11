package com.example.boot.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Ticket {
	private Integer ticketId;
	private String ticketAddress;
	private Integer ticketPrice;
	private Integer ticketCid;
}