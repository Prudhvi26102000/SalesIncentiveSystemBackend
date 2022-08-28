package com.example.sales.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Vehicle {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String type;
	private String name;
	private Long cost;
	
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vehicleId")
    @JsonIgnore
	private List<Commission> commission; 
    
    @JsonBackReference
    @OneToMany(cascade=CascadeType.ALL,mappedBy="vehicleId")
    @JsonIgnore
    private List<Sales_csv> sales_csv;
    
	
	public Vehicle() {
		
	}

	public Vehicle(int id, String type, String name, Long cost) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "vehicle [id=" + id + ", type=" + type + ", name=" + name + ", cost=" + cost
				+ "]";
	}
	
	

	
}
