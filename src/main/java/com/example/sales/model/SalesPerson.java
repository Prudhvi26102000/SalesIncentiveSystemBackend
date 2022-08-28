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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@JsonIgnoreProperties("inspection")
public class SalesPerson {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	private String city;
	private Long monthly_sales;
	private Long commission_value;
	private Long contact;
	
	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL,mappedBy="salesperson_id") 
	@JsonIgnore
	private List<TotalReport> totalreport;
	

	@JsonBackReference
	@OneToMany(cascade=CascadeType.ALL,mappedBy="salesperson_id")
	@JsonIgnore
	private List<Sales_csv> sales_csv;
	 
	public SalesPerson() {
		
	}

	public SalesPerson(int id, String email, String password, String city, Long monthly_sales,
			Long commission_value, Long contact) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.city = city;
		this.monthly_sales = monthly_sales;
		this.commission_value = commission_value;
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getMonthly_sales() {
		return monthly_sales;
	}

	public void setMonthly_sales(Long monthly_sales) {
		this.monthly_sales = monthly_sales;
	}

	public Long getCommission_value() {
		return commission_value;
	}

	public void setCommission_value(Long commission_value) {
		this.commission_value = commission_value;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "SalesPerson [id=" + id + ", email=" + email + ", password=" + password + ", city=" + city
				+ ", monthly_sales=" + monthly_sales + ", commission_value=" + commission_value + ", contact=" + contact
				+ "]";
	}
	
	
	

}
