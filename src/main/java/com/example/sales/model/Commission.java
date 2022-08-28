package com.example.sales.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Commission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Long min_range;
	private Long max_range;
	private int percentage;
	
	@ManyToOne
	@JoinColumn(name="vehicleId",nullable=false)
	private Vehicle vehicleId;
	
	public Commission() {
		
	}

	public Commission(int id, Long min_range, Long max_range, int percentage, Vehicle vehicleId) {
		super();
		this.id = id;
		this.min_range = min_range;
		this.max_range = max_range;
		this.percentage = percentage;
		this.vehicleId = vehicleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getMin_range() {
		return min_range;
	}

	public void setMin_range(Long min_range) {
		this.min_range = min_range;
	}

	public Long getMax_range() {
		return max_range;
	}

	public void setMax_range(Long max_range) {
		this.max_range = max_range;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public Vehicle getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Vehicle vehicleId) {
		this.vehicleId = vehicleId;
	}
	
	
	
	

	
}
