package com.example.sales.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="monthlyreport")
public class TotalReport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String month;
	private String year;
	private BigInteger commission_value;
	private BigInteger sales_value;
	
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="salesperson_id",nullable=false)
	private SalesPerson salesperson_id;
	
	public TotalReport()
	{
		
	}
	
	public TotalReport(int id, String month, String year, BigInteger commission_value, BigInteger sales_value,
			SalesPerson salesperson_id) {
		super();
		this.id = id;
		this.month = month;
		this.year = year;
		this.commission_value = commission_value;
		this.sales_value = sales_value;
		this.salesperson_id = salesperson_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigInteger getCommission_value() {
		return commission_value;
	}

	public void setCommission_value(BigInteger commission_value) {
		this.commission_value = commission_value;
	}

	public BigInteger getSales_value() {
		return sales_value;
	}

	public void setSales_value(BigInteger sales_value) {
		this.sales_value = sales_value;
	}

	public SalesPerson getSalesperson_id() {
		return salesperson_id;
	}

	public void setSalesperson_id(SalesPerson salesperson_id) {
		this.salesperson_id = salesperson_id;
	}
	 
	
	

}
