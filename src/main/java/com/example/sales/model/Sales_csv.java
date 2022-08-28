package com.example.sales.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
public class Sales_csv {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sales_id")
	private int id;
	@Column(name="cost")
	private Long cost;
	@Column(name="date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="vehicleId",nullable=false)
	private Vehicle vehicleId;
	

	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="salesperson_id",nullable=false)
	private SalesPerson salesperson_id;
	
	
//	public Sales_csv() {
//	}
//
//
//	public Sales_csv(int id, Long cost, Date date, Vehicle vehicleId, SalesPerson salesperson_id) {
//		super();
//		this.id = id;
//		this.cost = cost;
//		this.date = date;
//		this.vehicleId = vehicleId;
//		this.salesperson_id = salesperson_id;
//	}
//
//
//	public int getId() {
//		return id;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//
//	public Long getCost() {
//		return cost;
//	}
//
//
//	public void setCost(Long cost) {
//		this.cost = cost;
//	}
//
//
//	public Date getDate() {
//		return date;
//	}
//
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//
//	public Vehicle getVehicleId() {
//		return vehicleId;
//	}
//
//
//	public void setVehicleId(Vehicle vehicleId) {
//		this.vehicleId = vehicleId;
//	}
//
//
//	public SalesPerson getSalesperson_id() {
//		return salesperson_id;
//	}
//
//
//	public void setSalesperson_id(SalesPerson salesperson_id) {
//		this.salesperson_id = salesperson_id;
//	}
//
//


	
}
