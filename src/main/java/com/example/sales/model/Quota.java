package com.example.sales.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Quota {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String city;
	private Long limit;
	
	public Quota() {
		
	}

	public Quota(int id, String city, Long limit) {
		super();
		this.id = id;
		this.city = city;
		this.limit = limit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "Quota [id=" + id + ", city=" + city + ", limit=" + limit + "]";
	}
	
	

}
