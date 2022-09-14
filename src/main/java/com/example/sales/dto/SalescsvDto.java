package com.example.sales.dto;

import java.util.Date;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class SalescsvDto {
	

	private int id;
	private Long cost;
	private Date date;
	private int vehicleId;
	private int salesperson_id;
	private String type;
	private String name;

}
